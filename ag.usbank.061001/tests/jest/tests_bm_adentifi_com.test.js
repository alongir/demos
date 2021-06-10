const authenticate = require("./authentication");
const {clearSession, dataset, getHttpClient} = require("./up9lib");

describe.each(dataset("data/dataset_33.json"))("test_33_get_pixel_conv_param", (param) => {
    it("test_33_get_pixel_conv_param", () => {
        clearSession();

        // GET https://bm.adentifi.com/pixel/conv/{param} (endp 33)
        const bm_adentifi_com = getHttpClient("https://bm.adentifi.com", authenticate);
        return bm_adentifi_com.fetch("/pixel/conv/" + param)
        .then((response) => {
            expect(response.status).toEqual(302);
            return response.text();
        })
        .then((text) => {
        })
        .then((data) => {
        });
    });
});
