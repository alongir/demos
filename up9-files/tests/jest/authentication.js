function authenticate(targetKey, opts) {
    if (targetKey === "TARGET_CARTS") {
    } else if (targetKey === "TARGET_CATALOGUE") {
    } else if (targetKey === "TARGET_EDGE_ROUTER") {
    } else if (targetKey === "TARGET_FRONT_END") {
    } else if (targetKey === "TARGET_USER") {
    } else {
    }
}

module.exports = { authenticate }