const authenticate = require("./authentication");
const {JSONBuild, clearSession, getHttpTarget, uuidv4} = require("./up9lib");

it("test_68_post_shipping", () => {
    clearSession();

    // POST http://shipping/shipping (endp 68)
    const name = "57a98d98e4b00679b4a830b2";
    const shipping = getHttpTarget("TARGET_SHIPPING", authenticate);
    return shipping.fetch("/shipping", {
        method: "POST",
        headers: {
            "accept": "application/json",
            "content-type": "application/json"
        },
        body: JSONBuild("data/payload_for_endp_68.json", {
            "$.id": String(uuidv4()),
            "$.name": name
        })
    })
    .then((response) => {
        expect(response.status).toEqual(201);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});
