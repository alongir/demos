const authenticate = require("./authentication");
const {CSSselect, JSONBuild, clearSession, dataset, getHttpClient, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_45.json"))("test_45_post_param1_xwNT_param2_mpdLQ_param3_param4_IFsXW_param5", (param, param1, param2, param3, param4, sensor_data) => {
    it("test_45_post_param1_xwNT_param2_mpdLQ_param3_param4_IFsXW_param5", () => {
        clearSession();

        // POST https://onlinebanking.usbank.com/{param1}/xwNT/{param2}/mpdLQ/{param3}/{param4}/IFsXW/{param5} (endp 45)
        const onlinebanking_usbank_com = getHttpClient("https://onlinebanking.usbank.com", authenticate);
        return onlinebanking_usbank_com.fetch("/" + param + "/xwNT/" + param1 + "/mpdLQ/" + param2 + "/" + param3 + "/IFsXW/" + param4, {
            method: "POST",
            headers: {
                "content-type": "application/json"
            },
            body: JSONBuild("data/payload_for_endp_45.json", {
                "$.sensor_data": sensor_data
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
});

// authentication-related test
it("test_46_get_OLS_LoginAssist_RetriveId", () => {
    clearSession();

    // GET https://onlinebanking.usbank.com/OLS/LoginAssist/RetriveId (endp 46)
    const onlinebanking_usbank_com = getHttpClient("https://onlinebanking.usbank.com");
    return onlinebanking_usbank_com.fetch("/OLS/LoginAssist/RetriveId")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("div.mainBodyWidth div.body-content.bodyDiv div div.la__touchnav-container a", text)).toContain("Help");
        expect(CSSselect("html head title", text)).toContain("Login Assistance");
    })
    .then((data) => {
    });
});

it("test_47_get_OLS_Public_Enrollment_Index", () => {
    clearSession();

    // GET https://onlinebanking.usbank.com/OLS/Public/Enrollment/Index (endp 47)
    const onlinebanking_usbank_com = getHttpClient("https://onlinebanking.usbank.com", authenticate);
    return onlinebanking_usbank_com.fetch("/OLS/Public/Enrollment/Index" + urlencode([["isPartnerOLB", "true"]]))
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("html head title", text)).toContain("Enrollment");
    })
    .then((data) => {
    });
});
