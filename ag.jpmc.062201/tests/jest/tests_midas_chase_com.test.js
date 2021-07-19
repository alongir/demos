const authenticate = require("./authentication");
const {CSSselect, JSONPath, clearSession, dataset, getHttpClient, randomInteger, urlPart, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_33.json"))("test_033_get_stream_click", (pageID, param, param1, param2, param3, param4, param5, ssv_adf_traceid, ssv_locale, ssv_pageLayout, ssv_random, ssv_v1st, ssv_v1st1) => {
    it("test_033_get_stream_click", () => {
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
                            const ssv_midas_id = urlPart("?ssv_midas_id", JSONPath({
                                path: "$.offers[*].turl",
                                json: data
                            })[0]);
                            const ssv_transid = urlPart("?ssv_transid", JSONPath({
                                path: "$.offers[*].turl",
                                json: data
                            })[0]);

                            // GET https://midas.chase.com/stream/click (endp 33)
                            return midas_chase_com.fetch("/stream/click" + urlencode([["ssv_channel", "web"], ["ssv_creativeid", ssv_creativeid], ["ssv_locale", "en_us"], ["ssv_midas_id", ssv_midas_id], ["ssv_random", ssv_random], ["ssv_transid", ssv_transid], ["ssv_v1st", ssv_v1st1]]))
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

describe.each(dataset("data/dataset_34.json"))("test_034_get_stream_tag", (pageID, ssv_eid, ssv_tmc, ssv_v1st) => {
    it("test_034_get_stream_tag", () => {
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

describe.each(dataset("data/dataset_35.json"))("test_035_get_stream_view", (pageID, param, param1, param2, param3, param4, param5, ssv_adf_traceid, ssv_locale, ssv_locale1, ssv_pageLayout, ssv_v1st, ssv_v1st1) => {
    it("test_035_get_stream_view", () => {
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
                            const ssv_midas_id = urlPart("?ssv_midas_id", JSONPath({
                                path: "$.offers[*].turl",
                                json: data
                            })[0]);
                            const ssv_transid = urlPart("?ssv_transid", JSONPath({
                                path: "$.offers[*].turl",
                                json: data
                            })[0]);

                            // GET https://midas.chase.com/stream/view (endp 35)
                            return midas_chase_com.fetch("/stream/view" + urlencode([["ssv_channel", "web"], ["ssv_creativeid", ssv_creativeid], ["ssv_locale", ssv_locale1], ["ssv_midas_id", ssv_midas_id], ["ssv_pageLayout", "prospect_a"], ["ssv_random", String(randomInteger(220, 977))], ["ssv_transid", ssv_transid], ["ssv_v1st", ssv_v1st1]]))
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
