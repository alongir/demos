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

function randomEmail(domain=null) {
    const names = ['John', 'Peter', 'Bob', 'David', 'Harry']
    const surnames = ['Black', 'Clark', 'Duncan', 'Gibson', 'James']
    const emailDomains = ['gmail.com', 'yahoo.com', 'hotmail.com']

    username = `${names[randomInteger(0, names.length - 1)]}.${surnames[randomInteger(0, surnames.length - 1)]}`
        .toLowerCase()

    if (domain == null) {
        domain = emailDomains[randomInteger(0, emailDomains.length - 1)]
    }

    const email = `${username}@${domain}`
    return email;
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

module.exports = { uuidv4, randomInteger, randomFloat, randomEmail, urlPart, CSSselect }

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
    constructor(key, authCallback) {
        this.key = key;
        const baseAddrOverride = process.env[targetKey]
        if (baseAddrOverride != null) {
            this.baseAddr = baseAddrOverride;
        } else {
            this.baseAddr = JSON.parse(readFileSync('data/target_services.json'))[key]
        }

        if (authCallback != null) {
            this.authCallback = Object.values(authCallback)[0];
        } else {
            this.authCallback = null;
        }

        this.opts = fillOptsWithDefaults({});
        if (this.authCallback != null) {
            this.opts.configUrl = this.baseAddr;
            this.opts = this.authCallback(this.key, this.opts);
            if (this.opts != null && this.opts.hasOwnProperty('configUrl')) {
                delete this.opts.configUrl;
            }
        }
    }

    async fetch(url, opts) {
        url = this.baseAddr + url;
        opts = _.merge(this.opts, opts)
        opts.headers = {
            ...opts.headers,
            "Referer": this.baseAddr + "/"
        };
        const res = await fetch(url, opts);
        return res;
    }
}

function dataset(datasetPath) {
    return JSON.parse(readFileSync(datasetPath))["rows"].map(Object.values);
}

function resolveTargetKey(key) {
    targetKey = null;

    if (key.includes('://')) {
        regex = /\W|^(?=\d)/gi;
        const url = new URL(key);
        targetKey = url.hostname.replace(regex, '_');
        if (!targetKey || targetKey.length === 0) {
            targetKey = key.replace(regex, '_');
        }
        targetKey = 'TARGET_' + targetKey.toUpperCase();
    } else {
        targetKey = key
    }

    return targetKey;
}

function getHttpClient(key, authCallback=null) {
    return new Target(resolveTargetKey(key), authCallback);
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

function JSONBuild(jsonPath, obj) {
    let json = JSON.parse(readFileSync(jsonPath));
    Object.keys(obj).forEach(path => {
        _JSONPath.JSONPath({
            path: path,
            json: json,
            callback: (v, _dummy, p) => p.parent[p.parentProperty]=obj[path]
        });
    });

    return JSON.stringify(json);
}

module.exports = { ...module.exports, dataset, getHttpClient, token, fetch, clearSession, urlencode, JSONPath, JSONBuild, readFileSync }
