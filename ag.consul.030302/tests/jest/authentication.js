function authenticate(targetKey, opts) {
    if (targetKey === "TARGET_CARTS") {
    } else if (targetKey === "TARGET_CATALOGUE") {
    } else if (targetKey === "TARGET_FRONT_END") {
    } else if (targetKey === "TARGET_MOCKINTOSH") {
    } else if (targetKey === "TARGET_ORDERS") {
    } else if (targetKey === "TARGET_USER") {
    } else {
    }
}

module.exports = { authenticate }