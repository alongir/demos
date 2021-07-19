const authenticate = require("./authentication");
const {CSSselect, JSONBuild, JSONPath, clearSession, dataset, getHttpClient, randomInteger, urlPart, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_31.json"))("test_031_post_rest_v1_delivery", (client, colorDepth, height, height1, heroeId, locationHint, logging, marketingCloudVisitorId, mboxName, name, pageID, pageTitle, param, param1, param2, param3, param4, param5, param6, pixelRatio, profileParameters, q, requestId, sessionId, ssv_adf_traceid, ssv_locale, ssv_pageLayout, ssv_v1st, timeOffsetInMinutes, tntId, token_, userAgent, version, webGLRenderer, width, width1) => {
    it("test_031_post_rest_v1_delivery", () => {
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
                            const cid = urlPart("/7", JSONPath({
                                path: "$.offers[*].cid",
                                json: data
                            })[0]);

                            // GET https://sites.chase.com/content/Creatives/Public/Heroes/{heroeId}/March/{cid} (endp 36)
                            const sites_chase_com = getHttpClient("https://sites.chase.com", authenticate);
                            return sites_chase_com.fetch("/content/Creatives/Public/Heroes/" + heroeId + "/March/" + cid)
                            .then((response) => {
                                expect(response.status).toEqual(200);
                                return response.text();
                            })
                            .then((text) => {
                            })
                            .then((data) => {
                                // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/carousel-single-images_alt/{param2}/module.html (endp 19)
                                return www_chase_com.fetch("/content/chase-ux/en/structured/module/" + param + "/carousel-single-images_alt/" + param6 + "/module.html", {
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
                                    // GET https://www.chase.com/personal/investments/advisor (endp 28)
                                    return www_chase_com.fetch("/personal/investments/advisor")
                                    .then((response) => {
                                        expect(response.status).toEqual(200);
                                        return response.text();
                                    })
                                    .then((text) => {
                                        expect(CSSselect("main#main h1.accessible-text", text)).toContain("J.P. Morgan Financial Advisors");
                                        expect(CSSselect("html head title", text)).toContain("Connect with a J.P. Morgan Financial Advisor | Chase.com");
                                    })
                                    .then((data) => {
                                        // GET https://locator.chase.com/search (endp 76)
                                        const locator_chase_com = getHttpClient("https://locator.chase.com", authenticate);
                                        return locator_chase_com.fetch("/search" + urlencode([["q", q]]))
                                        .then((response) => {
                                            expect(response.status).toEqual(200);
                                            return response.text();
                                        })
                                        .then((text) => {
                                            expect(CSSselect("div#js-locator div.Locator-content h1.Locator-title.Heading--locator.js-Locator-title", text)).toContain("Find a Chase ATM or branch near you");
                                            expect(CSSselect("html head title", text)).toContain("Branches and ATMs | Chase Bank");
                                            const referringUrl = CSSselect("header#Header div.Header-wrapper.l-container div.Header-mainRow div.Header-left div div.js-focustrap div div a.Header-expandedLink--atm.Text--expanded[href] @href", response).text().trim();
                                            const url = CSSselect("header#Header div.Header-wrapper.l-container div.Header-mainRow div.Header-left div div.js-focustrap div a.Text--expanded[href] @href", response).text().trim();
                                        })
                                        .then((data) => {
                                            // POST https://target.chase.com/rest/v1/delivery (endp 31)
                                            const target_chase_com = getHttpClient("https://target.chase.com", authenticate);
                                            return target_chase_com.fetch("/rest/v1/delivery" + urlencode([["client", client], ["sessionId", sessionId], ["version", version]]), {
                                                method: "POST",
                                                headers: {
                                                    "content-type": "application/json"
                                                },
                                                body: JSONBuild("data/payload_for_endp_31.json", {
                                                    "$.context.address.referringUrl": referringUrl,
                                                    "$.context.address.url": url,
                                                    "$.context.browser.webGLRenderer": webGLRenderer,
                                                    "$.context.screen.colorDepth": colorDepth,
                                                    "$.context.screen.height": height,
                                                    "$.context.screen.pixelRatio": pixelRatio,
                                                    "$.context.screen.width": width,
                                                    "$.context.timeOffsetInMinutes": timeOffsetInMinutes,
                                                    "$.context.userAgent": userAgent,
                                                    "$.context.window.height": height1,
                                                    "$.context.window.width": width1,
                                                    "$.execute.mboxes[*].name": name,
                                                    "$.execute.mboxes[*].parameters.mboxName": mboxName,
                                                    "$.execute.mboxes[*].parameters.pageTitle": pageTitle,
                                                    "$.execute.mboxes[*].profileParameters.*": profileParameters,
                                                    "$.experienceCloud.analytics.logging": logging,
                                                    "$.experienceCloud.audienceManager.locationHint": locationHint,
                                                    "$.id.marketingCloudVisitorId": marketingCloudVisitorId,
                                                    "$.id.tntId": tntId,
                                                    "$.property.token": token_,
                                                    "$.requestId": requestId
                                                })
                                            })
                                            .then((response) => {
                                                expect(response.status).toEqual(200);
                                                return response.text();
                                            })
                                            .then((text) => {
                                                return JSON.parse(text);
                                            })
                                            .then((data) => {
                                                expect(JSONPath({
                                                    path: "$.execute.mboxes[*].analytics.payload.pe",
                                                    json: data
                                                })).toContain("tnt");
                                            });
                                        });
                                    });
                                });
                            });
                        });
                    });
                });
            });
        });
    });
});
