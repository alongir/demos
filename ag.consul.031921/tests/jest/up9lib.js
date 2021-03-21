const fs = require("fs");
const tough = require("tough-cookie");
const isomorphicUnfetch = require("isomorphic-unfetch");
const _ = require("lodash");
const _JSONPath = require("jsonpath-plus");
const url = require('url');
const cheerio = require('cheerio');

function uuidv4() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}

function randomInteger(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

function randomFloat(min, max) {
    return Math.random() * (max - min) + min;
}

function urlPart(ospec, paramURL) {
    const flag = ospec[0];
    const spec = ospec.slice(1, ospec.length);

    const urlParsed = url.parse(paramURL, true);
    if (flag == '/') {
        const ind = parseInt(spec);
        const pathParts = urlParsed.pathname.split('/')
        return pathParts[ind]
    } else {
        return urlParsed.searchParams.get(spec)
    }
}

function CSSselect(query, html) {
    const $ = cheerio.load(html);
    const result = $(query).map(function(i, el) {
        return $(this).text().trim();
    }).get().join('');
    return result;
}

module.exports = { uuidv4, randomInteger, urlPart, CSSselect }

let fetch;

const cookieJar = new tough.CookieJar()

if (typeof module !== 'undefined' && module.exports) {
    fetch = require('fetch-cookie')(isomorphicUnfetch, cookieJar);
} else {
    fetch = isomorphicUnfetch;
}

fetchDefaults = {
    // These properties are part of the Fetch Standard
    method: 'GET',
    // except the headers
    headers: {
        "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_1) AppleWebKit/537.36 \
        (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36",
        "Accept": "*/*",
        "x-abuse-info": "UP9.com generated tests"
    },
    body: null,
    redirect: 'follow',
    signal: null,
}

if (typeof module !== 'undefined' && module.exports) {
    fetchDefaults = {
        ...fetchDefaults,

        // The following properties are node-fetch extensions
        follow: 20,
        timeout: 0,
        compress: true,
        size: 0,
        agent: null
    }
}

function fillOptsWithDefaults(opts) {
    if (opts == null) {
        opts = {}
    }

    Object.keys(fetchDefaults).forEach(property => {
        if (!(property in opts)) {
            opts[property] = fetchDefaults[property];
        }
    });

    return opts;
}

function readFileSync(filepath, flag="r") {
    return fs.readFileSync(filepath, {
        encoding: "utf8",
        flag: flag
    });
}

class Target {
    constructor(key, auth_callback) {
        this.key = key;
        this.hostname = JSON.parse(readFileSync('data/target_services.json'))[this.key];
        if (auth_callback != null) {
            this.auth_callback = Object.values(auth_callback)[0];
        } else {
            this.auth_callback = null;
        }

        this.opts = fillOptsWithDefaults({});
        if (this.auth_callback != null) {
            this.opts = this.auth_callback(this.key, this.opts);
        }
    }

    async fetch(url, opts) {
        url = this.hostname + url;
        opts = _.merge(this.opts, opts)
        opts.headers = {
            ...opts.headers,
            "Referer": this.hostname + "/"
        };
        const res = await fetch(url, opts);
        return res;
    }
}

function dataset(dataset_path) {
    return JSON.parse(readFileSync(dataset_path))["rows"].map(Object.values);
}

function getHttpTarget(key, auth_callback=null) {
    return new Target(key, auth_callback);
}

function token(key, callback = undefined) {
    const token = JSON.parse(readFileSync('data/auth_data.json'))[key][0];

    if (typeof callback === 'function') {
        return callback(token);
    } else {
        return token;
    }
}

function clearSession() {
    cookieJar.removeAllCookiesSync();
}

function urlencode(components) {
    qs = ""
    components.forEach(component => {
        if (!!qs) {
            qs += "&";
        } else {
            qs += "?";
        }
        qs += `${component[0]}=${encodeURIComponent(component[1])}`;
    });
    return qs;
}

function JSONPath(obj) {
    const match = _JSONPath.JSONPath(obj);
    if (match == null) {
        throw 'JSONPath does not match!';
    }
    return match;
}

function JSONBuild(json_path, obj) {
    let json = JSON.parse(readFileSync(json_path));
    Object.keys(obj).forEach(path => {
        _JSONPath.JSONPath({
            path: path,
            json: json,
            callback: (v, _dummy, p) => p.parent[p.parentProperty]=obj[path]
        });
    });

    return JSON.stringify(json);
}

module.exports = { ...module.exports, dataset, getHttpTarget, token, fetch, clearSession, urlencode, JSONPath, JSONBuild, readFileSync }
