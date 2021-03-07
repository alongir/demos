const authenticate = require("./authentication");
const {clearSession, getHttpTarget, urlencode} = require("./up9lib");

it("test_14_get_orders_search_customerId", () => {
    clearSession();

    // GET http://orders/orders/search/customerId (endp 14)
    const orders = getHttpTarget("TARGET_ORDERS", authenticate);
    return orders.fetch("/orders/search/customerId" + urlencode([["custId", "undefined"], ["sort", "date"]]))
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});
