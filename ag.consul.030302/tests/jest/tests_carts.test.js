const authenticate = require("./authentication");
const {clearSession, getHttpTarget, urlencode} = require("./up9lib");

it("test_13_get_carts_id_items", () => {
    clearSession();

    // GET http://carts/carts/{id}/items (endp 13)
    const id = "57a98d98e4b00679b4a830b2";
    const carts = getHttpTarget("TARGET_CARTS", authenticate);
    return carts.fetch("/carts/" + id + "/items")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_12_get_carts_id_merge", () => {
    clearSession();

    // GET http://carts/carts/{id}/merge (endp 12)
    const id = "57a98d98e4b00679b4a830b2";
    const sessionId = "7w4TGI0pnIxn5TEFoHX6O2spPdXa3dut";
    const carts = getHttpTarget("TARGET_CARTS", authenticate);
    return carts.fetch("/carts/" + id + "/merge" + urlencode([["sessionId", sessionId]]))
    .then((response) => {
        expect(response.status).toEqual(202);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});
