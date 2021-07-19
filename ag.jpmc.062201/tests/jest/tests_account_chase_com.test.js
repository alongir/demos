const authenticate = require("./authentication");
const {CSSselect, JSONPath, clearSession, dataset, getHttpClient, randomInteger, urlPart, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_58.json"))("test_058_get_consumer_banking_seo", (pageID, param, param1, param2, param3, param4, param5, ssv_adf_traceid, ssv_locale, ssv_pageLayout, ssv_v1st) => {
    it("test_058_get_consumer_banking_seo", () => {
        clearSession();

        // GET https://www.chase.com/ (endp 1)
        const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
        return www_chase_com.fetch("/")
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("main#main h1.accessible-text", text)).toContain("Chase.com home");
            expect(CSSselect("html head title", text)).toContain("Credit Card, Mortgage, Banking, Auto | Chase Online | Chase.com");
        })
        .then((data) => {
            // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/primary-triplet/{param2}/module.html (endp 16)
            return www_chase_com.fetch("/content/chase-ux/en/structured/module/" + param + "/primary-triplet/" + param1 + "/module.html", {
                headers: {
                    "x-requested-with": "XMLHttpRequest"
                }
            })
            .then((response) => {
                expect(response.status).toEqual(200);
                return response.text();
            })
            .then((text) => {
            })
            .then((data) => {
                // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/ad-geo/{param2}/module.html (endp 21)
                return www_chase_com.fetch("/content/chase-ux/en/structured/module/" + param + "/ad-geo/" + param2 + "/module.html", {
                    headers: {
                        "x-requested-with": "XMLHttpRequest"
                    }
                })
                .then((response) => {
                    expect(response.status).toEqual(200);
                    return response.text();
                })
                .then((text) => {
                })
                .then((data) => {
                    // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/index/{param2}/{param3}/module.html (endp 22)
                    return www_chase_com.fetch("/content/chase-ux/en/structured/module/" + param + "/index/" + param3 + "/" + param4 + "/module.html", {
                        headers: {
                            "x-requested-with": "XMLHttpRequest"
                        }
                    })
                    .then((response) => {
                        expect(response.status).toEqual(200);
                        return response.text();
                    })
                    .then((text) => {
                    })
                    .then((data) => {
                        // GET https://midas.chase.com/prweb/PRRestService/{param}/v1/MakeDecision (endp 32)
                        const midas_chase_com = getHttpClient("https://midas.chase.com", authenticate);
                        return midas_chase_com.fetch("/prweb/PRRestService/" + param5 + "/v1/MakeDecision" + urlencode([["pageID", pageID], ["ssv_accttype", ""], ["ssv_adf_traceid", ssv_adf_traceid], ["ssv_channel", "web"], ["ssv_cigseg", ""], ["ssv_eci", ""], ["ssv_locale", ssv_locale], ["ssv_origin", ""], ["ssv_pageLayout", ssv_pageLayout], ["ssv_pfid", ""], ["ssv_pnpc", ""], ["ssv_product", ""], ["ssv_random", String(randomInteger(220, 977))], ["ssv_siteacct", ""], ["ssv_sitebrand", ""], ["ssv_userType", ""], ["ssv_v1st", ssv_v1st], ["ssv_zip", ""], ["ssvm_lids", ""], ["ssvm_pnpcs", ""], ["ssvm_products", ""], ["time", String(Date.now())]]))
                        .then((response) => {
                            expect(response.status).toEqual(200);
                            return response.text();
                        })
                        .then((text) => {
                            return JSON.parse(text);
                        })
                        .then((data) => {
                            expect(JSONPath({
                                path: "$.offers[*].ctype",
                                json: data
                            })).toContain("html");
                            const ssv_creativeid = urlPart("?ssv_creativeid", JSONPath({
                                path: "$.offers[*].turl",
                                json: data
                            })[0]);

                            // GET https://account.chase.com/consumer/banking/seo (endp 58)
                            const account_chase_com = getHttpClient("https://account.chase.com", authenticate);
                            return account_chase_com.fetch("/consumer/banking/seo" + urlencode([["jp_aid_a", ssv_creativeid], ["jp_aid_p", "retail_checking_hp/tile"]]))
                            .then((response) => {
                                expect(response.status).toEqual(200);
                                return response.text();
                            })
                            .then((text) => {
                                expect(CSSselect("div#leaving-modal div.leaving-modal-center div.leaving-modal-title h1", text)).toContain("You're Now Leaving Chase");
                            })
                            .then((data) => {
                            });
                        });
                    });
                });
            });
        });
    });
});

describe.each(dataset("data/dataset_59.json"))("test_059_post_consumer_banking_seo", (Payload_ContactPoint, Payload_ProductSelection, form_build_id, pageID, param, param1, param2, param3, param4, param5, referingURL, ssv_adf_traceid, ssv_locale, ssv_pageLayout, ssv_v1st) => {
    it("test_059_post_consumer_banking_seo", () => {
        clearSession();

        // GET https://www.chase.com/ (endp 1)
        const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
        return www_chase_com.fetch("/")
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("main#main h1.accessible-text", text)).toContain("Chase.com home");
            expect(CSSselect("html head title", text)).toContain("Credit Card, Mortgage, Banking, Auto | Chase Online | Chase.com");
        })
        .then((data) => {
            // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/primary-triplet/{param2}/module.html (endp 16)
            return www_chase_com.fetch("/content/chase-ux/en/structured/module/" + param + "/primary-triplet/" + param1 + "/module.html", {
                headers: {
                    "x-requested-with": "XMLHttpRequest"
                }
            })
            .then((response) => {
                expect(response.status).toEqual(200);
                return response.text();
            })
            .then((text) => {
            })
            .then((data) => {
                // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/ad-geo/{param2}/module.html (endp 21)
                return www_chase_com.fetch("/content/chase-ux/en/structured/module/" + param + "/ad-geo/" + param2 + "/module.html", {
                    headers: {
                        "x-requested-with": "XMLHttpRequest"
                    }
                })
                .then((response) => {
                    expect(response.status).toEqual(200);
                    return response.text();
                })
                .then((text) => {
                })
                .then((data) => {
                    // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/index/{param2}/{param3}/module.html (endp 22)
                    return www_chase_com.fetch("/content/chase-ux/en/structured/module/" + param + "/index/" + param3 + "/" + param4 + "/module.html", {
                        headers: {
                            "x-requested-with": "XMLHttpRequest"
                        }
                    })
                    .then((response) => {
                        expect(response.status).toEqual(200);
                        return response.text();
                    })
                    .then((text) => {
                    })
                    .then((data) => {
                        // GET https://midas.chase.com/prweb/PRRestService/{param}/v1/MakeDecision (endp 32)
                        const midas_chase_com = getHttpClient("https://midas.chase.com", authenticate);
                        return midas_chase_com.fetch("/prweb/PRRestService/" + param5 + "/v1/MakeDecision" + urlencode([["pageID", pageID], ["ssv_accttype", ""], ["ssv_adf_traceid", ssv_adf_traceid], ["ssv_channel", "web"], ["ssv_cigseg", ""], ["ssv_eci", ""], ["ssv_locale", ssv_locale], ["ssv_origin", ""], ["ssv_pageLayout", ssv_pageLayout], ["ssv_pfid", ""], ["ssv_pnpc", ""], ["ssv_product", ""], ["ssv_random", String(randomInteger(220, 977))], ["ssv_siteacct", ""], ["ssv_sitebrand", ""], ["ssv_userType", ""], ["ssv_v1st", ssv_v1st], ["ssv_zip", ""], ["ssvm_lids", ""], ["ssvm_pnpcs", ""], ["ssvm_products", ""], ["time", String(Date.now())]]))
                        .then((response) => {
                            expect(response.status).toEqual(200);
                            return response.text();
                        })
                        .then((text) => {
                            return JSON.parse(text);
                        })
                        .then((data) => {
                            expect(JSONPath({
                                path: "$.offers[*].ctype",
                                json: data
                            })).toContain("html");
                            const ssv_creativeid = urlPart("?ssv_creativeid", JSONPath({
                                path: "$.offers[*].turl",
                                json: data
                            })[0]);

                            // POST https://account.chase.com/consumer/banking/seo (endp 59)
                            const account_chase_com = getHttpClient("https://account.chase.com", authenticate);
                            return account_chase_com.fetch("/consumer/banking/seo" + urlencode([["jp_aid_a", ssv_creativeid], ["jp_aid_p", "retail_checking_hp/tile"]]), {
                                method: "POST",
                                headers: {
                                    "content-type": "application/x-www-form-urlencoded",
                                    "x-requested-with": "XMLHttpRequest"
                                },
                                body: new URLSearchParams({
                                    "Payload_ContactPoint": Payload_ContactPoint,
                                    "Payload_IsDaoWithEmailSubmission": "false",
                                    "Payload_IsEmailSubmission": "true",
                                    "Payload_ProductSelection": Payload_ProductSelection,
                                    "Payload_ValidationMsg": "",
                                    "device_type": "Desktop",
                                    "form_build_id": form_build_id,
                                    "form_id": "email_my_coupon",
                                    "gclid": "",
                                    "optimizelyID": "default",
                                    "prd_link": "",
                                    "referingURL": referingURL,
                                    "v1stCookie": ""
                                })
                            })
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
                });
            });
        });
    });
});
