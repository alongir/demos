const authenticate = require("./authentication");
const {clearSession, dataset, getHttpClient} = require("./up9lib");

describe.each(dataset("data/dataset_30.json"))("test_30_get_pixel_conv_param", (param) => {
    it("test_30_get_pixel_conv_param", () => {
        clearSession();

        // GET https://bm.adentifi.com/pixel/conv/{param} (endp 30)
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
