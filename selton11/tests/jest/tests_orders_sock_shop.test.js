const authenticate = require("./authentication");
const {clearSession, dataset, getHttpClient, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_38.json"))("test_38_get_orders_search_customerId", (custId) => {
    it("test_38_get_orders_search_customerId", () => {
        clearSession();

        // GET http://orders.sock-shop/orders/search/customerId (endp 38)
        const orders_sock_shop = getHttpClient("http://orders.sock-shop", authenticate);
        return orders_sock_shop.fetch("/orders/search/customerId" + urlencode([["custId", custId], ["sort", "date"]]))
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
        })
        .then((data) => {
        });
    });
});
