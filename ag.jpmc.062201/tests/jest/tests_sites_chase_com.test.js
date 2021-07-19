const authenticate = require("./authentication");
const {CSSselect, JSONPath, clearSession, dataset, getHttpClient, randomInteger, urlPart, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_102.json"))("test_102_get_content_Creatives_Public_Heroes_heroeId_June_cid", (heroeId, pageID, param, param1, param2, param3, param4, param5, ssv_adf_traceid, ssv_locale, ssv_pageLayout, ssv_v1st) => {
    it("test_102_get_content_Creatives_Public_Heroes_heroeId_June_cid", () => {
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

                            // GET https://sites.chase.com/content/Creatives/Public/Heroes/{heroeId}/June/{cid} (endp 102)
                            const sites_chase_com = getHttpClient("https://sites.chase.com", authenticate);
                            return sites_chase_com.fetch("/content/Creatives/Public/Heroes/" + heroeId + "/June/" + cid)
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

describe.each(dataset("data/dataset_37.json"))("test_037_get_content_Creatives_Public_Tiles_tileId_March_cid", (pageID, param, param1, param2, param3, param4, param5, ssv_adf_traceid, ssv_locale, ssv_pageLayout, ssv_v1st, tileId) => {
    it("test_037_get_content_Creatives_Public_Tiles_tileId_March_cid", () => {
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

                            // GET https://sites.chase.com/content/Creatives/Public/Tiles/{tileId}/March/{cid} (endp 37)
                            const sites_chase_com = getHttpClient("https://sites.chase.com", authenticate);
                            return sites_chase_com.fetch("/content/Creatives/Public/Tiles/" + tileId + "/March/" + cid)
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

describe.each(dataset("data/dataset_38.json"))("test_038_get_content_Creatives_Public_Triplets_tripletId_July_cid", (pageID, param, param1, param2, param3, param4, param5, ssv_adf_traceid, ssv_locale, ssv_pageLayout, ssv_v1st, tripletId) => {
    it("test_038_get_content_Creatives_Public_Triplets_tripletId_July_cid", () => {
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

                            // GET https://sites.chase.com/content/Creatives/Public/Triplets/{tripletId}/July/{cid} (endp 38)
                            const sites_chase_com = getHttpClient("https://sites.chase.com", authenticate);
                            return sites_chase_com.fetch("/content/Creatives/Public/Triplets/" + tripletId + "/July/" + cid)
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

describe.each(dataset("data/dataset_103.json"))("test_103_get_content_Creatives_Public_Triplets_tripletId_March_cid", (pageID, param, param1, param2, param3, param4, param5, ssv_adf_traceid, ssv_locale, ssv_pageLayout, ssv_v1st, tripletId) => {
    it("test_103_get_content_Creatives_Public_Triplets_tripletId_March_cid", () => {
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

                            // GET https://sites.chase.com/content/Creatives/Public/Triplets/{tripletId}/March/{cid} (endp 103)
                            const sites_chase_com = getHttpClient("https://sites.chase.com", authenticate);
                            return sites_chase_com.fetch("/content/Creatives/Public/Triplets/" + tripletId + "/March/" + cid)
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

describe.each(dataset("data/dataset_40.json"))("test_040_get_content_Creatives_Public_Triplets_tripletId_May_cid", (pageID, param, param1, param2, param3, param4, param5, ssv_adf_traceid, ssv_locale, ssv_pageLayout, ssv_v1st, tripletId) => {
    it("test_040_get_content_Creatives_Public_Triplets_tripletId_May_cid", () => {
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

                            // GET https://sites.chase.com/content/Creatives/Public/Triplets/{tripletId}/May/{cid} (endp 40)
                            const sites_chase_com = getHttpClient("https://sites.chase.com", authenticate);
                            return sites_chase_com.fetch("/content/Creatives/Public/Triplets/" + tripletId + "/May/" + cid)
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

describe.each(dataset("data/dataset_39.json"))("test_039_get_content_Creatives_Public_Triplets_tripletId_November_cid", (pageID, param, param1, param2, param3, param4, param5, ssv_adf_traceid, ssv_locale, ssv_pageLayout, ssv_v1st, tripletId) => {
    it("test_039_get_content_Creatives_Public_Triplets_tripletId_November_cid", () => {
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

                            // GET https://sites.chase.com/content/Creatives/Public/Triplets/{tripletId}/November/{cid} (endp 39)
                            const sites_chase_com = getHttpClient("https://sites.chase.com", authenticate);
                            return sites_chase_com.fetch("/content/Creatives/Public/Triplets/" + tripletId + "/November/" + cid)
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
