function authenticate(targetKey, opts) {
    if (opts.configUrl === "http://carts") {
    } else if (opts.configUrl === "http://catalogue") {
    } else if (opts.configUrl === "http://front-end") {
    } else if (opts.configUrl === "http://orders") {
    } else if (opts.configUrl === "http://payment") {
    } else if (opts.configUrl === "http://shipping") {
    } else if (opts.configUrl === "http://user") {
    } else {
    }
}

module.exports = { authenticate }