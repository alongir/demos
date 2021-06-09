const authenticate = require("./authentication");
const {clearSession, getHttpClient, urlencode} = require("./up9lib");

it("test_26_get_catalogue_size", () => {
    clearSession();

    // GET http://catalogue.sock-shop/catalogue/size (endp 26)
    const catalogue_sock_shop = getHttpClient("http://catalogue.sock-shop", authenticate);
    return catalogue_sock_shop.fetch("/catalogue/size" + urlencode([["tags", ""]]))
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_27_get_tags", () => {
    clearSession();

    // GET http://catalogue.sock-shop/tags (endp 27)
    const catalogue_sock_shop = getHttpClient("http://catalogue.sock-shop", authenticate);
    return catalogue_sock_shop.fetch("/tags")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});
