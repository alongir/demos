function authenticate(targetKey, opts) {
    if (opts.configUrl === "http://carts.sock-shop") {
    } else if (opts.configUrl === "http://catalogue.sock-shop") {
    } else if (opts.configUrl === "http://front-end.sock-shop") {
    } else if (opts.configUrl === "kafka://kafka") {
    } else if (opts.configUrl === "http://orders.sock-shop") {
    } else if (opts.configUrl === "http://payment.sock-shop") {
    } else if (opts.configUrl === "http://shipping.sock-shop") {
    } else if (opts.configUrl === "http://user.sock-shop") {
    } else {
    }
}

module.exports = { authenticate }