const authenticate = require("./authentication");
const {clearSession, dataset, getHttpClient, randomInteger, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_33.json"))("test_33_get_stream_click", (ssv_creativeid, ssv_midas_id, ssv_random, ssv_transid, ssv_v1st) => {
    it("test_33_get_stream_click", () => {
        clearSession();

        // GET https://midas.chase.com/stream/click (endp 33)
        const midas_chase_com = getHttpClient("https://midas.chase.com", authenticate);
        return midas_chase_com.fetch("/stream/click" + urlencode([["ssv_channel", "web"], ["ssv_creativeid", ssv_creativeid], ["ssv_locale", "en_us"], ["ssv_midas_id", ssv_midas_id], ["ssv_random", ssv_random], ["ssv_transid", ssv_transid], ["ssv_v1st", ssv_v1st]]))
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

describe.each(dataset("data/dataset_34.json"))("test_34_get_stream_tag", (pageID, ssv_eid, ssv_tmc, ssv_v1st) => {
    it("test_34_get_stream_tag", () => {
        clearSession();

        // GET https://midas.chase.com/stream/tag (endp 34)
        const midas_chase_com = getHttpClient("https://midas.chase.com", authenticate);
        return midas_chase_com.fetch("/stream/tag" + urlencode([["pageID", pageID], ["ssv_eci", ""], ["ssv_eid", ssv_eid], ["ssv_pfid", ""], ["ssv_productid", ""], ["ssv_src", ""], ["ssv_tmc", ssv_tmc], ["ssv_v1st", ssv_v1st]]))
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

describe.each(dataset("data/dataset_35.json"))("test_35_get_stream_view", (ssv_creativeid, ssv_locale, ssv_midas_id, ssv_v1st) => {
    it("test_35_get_stream_view", () => {
        clearSession();

        // GET https://midas.chase.com/stream/view (endp 35)
        const midas_chase_com = getHttpClient("https://midas.chase.com", authenticate);
        return midas_chase_com.fetch("/stream/view" + urlencode([["ssv_channel", "web"], ["ssv_creativeid", ssv_creativeid], ["ssv_locale", ssv_locale], ["ssv_midas_id", ssv_midas_id], ["ssv_pageLayout", "prospect_a"], ["ssv_random", String(randomInteger(220, 977))], ["ssv_transid", String(randomInteger(-8655682384752228352, 2808894671955032576))], ["ssv_v1st", ssv_v1st]]))
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
