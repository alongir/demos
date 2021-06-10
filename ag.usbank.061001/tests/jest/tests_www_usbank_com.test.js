const authenticate = require("./authentication");
const {CSSselect, JSONBuild, JSONPath, clearSession, dataset, getHttpClient, urlPart, urlencode} = require("./up9lib");

it("test_01_get_about_us_bank_customer_service_html", () => {
    clearSession();

    // GET https://www.usbank.com/about-us-bank/customer-service.html (endp 1)
    const www_usbank_com = getHttpClient("https://www.usbank.com", authenticate);
    return www_usbank_com.fetch("/about-us-bank/customer-service.html")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("html head title", text)).toContain("Customer service | U.S. Bank");
    })
    .then((data) => {
    });
});

it("test_02_get_bank_accounts_checking_accounts_html", () => {
    clearSession();

    // GET https://www.usbank.com/bank-accounts/checking-accounts.html (endp 2)
    const www_usbank_com = getHttpClient("https://www.usbank.com", authenticate);
    return www_usbank_com.fetch("/bank-accounts/checking-accounts.html")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("dialog.dialog.shield-zipcodes[name=\"#saveXZip\"] div.content div.heading.large h1", text)).toContain("Zip Code");
        expect(CSSselect("html head title", text)).toContain("Checking accounts | Open a Personal Checking Account | U.S. Bank");
    })
    .then((data) => {
    });
});

it("test_03_get_bank_accounts_checking_accounts_gold_checking_account_html", () => {
    clearSession();

    // GET https://www.usbank.com/bank-accounts/checking-accounts/gold-checking-account.html (endp 3)
    const www_usbank_com = getHttpClient("https://www.usbank.com", authenticate);
    return www_usbank_com.fetch("/bank-accounts/checking-accounts/gold-checking-account.html")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("section.pubIns.productDetailsPage div div.bodyContent.container-fluid div.row div.bannerResponsiveGrid div div.aem-Grid div.banner.parbase.aem-GridColumn div.USBDesignSystem--Shield.USBHero div div.USBHero__Container.clearfix div.clearfix div.text div div.textContainer h1", text)).toContain("U.S. BANK GOLD CHECKING PACKAGE");
        expect(CSSselect("html head title", text)).toContain("Gold Checking account | Personal Checking account | U.S. Bank");
    })
    .then((data) => {
    });
});

it("test_81_get_corporate_and_commercial_banking_solutions_credit_and_financing_html", () => {
    clearSession();

    // GET https://www.usbank.com/corporate-and-commercial-banking/solutions/credit-and-financing.html (endp 81)
    const www_usbank_com = getHttpClient("https://www.usbank.com", authenticate);
    return www_usbank_com.fetch("/corporate-and-commercial-banking/solutions/credit-and-financing.html")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("a#continue", text)).toContain("Continue");
    })
    .then((data) => {
    });
});

it("test_49_get_credit_cards_href", () => {
    clearSession();

    // GET https://www.usbank.com/index.html (endp 4)
    const www_usbank_com = getHttpClient("https://www.usbank.com", authenticate);
    return www_usbank_com.fetch("/index.html")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("html head title", text)).toContain("Consumer banking | Personal banking | U.S. Bank");
        const href = urlPart("/2", CSSselect("div.USBContent main.bodyContent div.aem-Grid div.responsivegrid.aem-GridColumn div.aem-Grid div.banner.parbase.aem-GridColumn div.USBHero div div div div div a[href] @href", response).text().trim());
        const c3ch = urlPart("?c3ch", CSSselect("div.USBContent main.bodyContent div.aem-Grid div.responsivegrid.aem-GridColumn div.aem-Grid div.banner.parbase.aem-GridColumn div.USBHero div div div div div a[href] @href", response).text().trim());
        const c3nid = urlPart("?icid", CSSselect("div.USBContent main.bodyContent div.aem-Grid div.responsivegrid.aem-GridColumn div.aem-Grid div.banner.parbase.aem-GridColumn div.USBHero div div div div div a[href] @href", response).text().trim());
        const icid = urlPart("?icid", CSSselect("div.USBContent main.bodyContent div.aem-Grid div.responsivegrid.aem-GridColumn div.aem-Grid div.banner.parbase.aem-GridColumn div.USBHero div div div div div a[href] @href", response).text().trim());
    })
    .then((data) => {
        // GET https://www.usbank.com/credit-cards/{href} (endp 49)
        return www_usbank_com.fetch("/credit-cards/" + href + urlencode([["c3ch", c3ch], ["c3nid", c3nid], ["icid", icid]]))
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("a#continue", text)).toContain("Continue");
        })
        .then((data) => {
        });
    });
});

it("test_50_get_home_loans_mortgage_first_time_home_buyers_html", () => {
    clearSession();

    // GET https://www.usbank.com/home-loans/mortgage/first-time-home-buyers.html (endp 50)
    const www_usbank_com = getHttpClient("https://www.usbank.com", authenticate);
    return www_usbank_com.fetch("/home-loans/mortgage/first-time-home-buyers.html")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("section.pubIns div.bodyContent.container-fluid div.row.minHeightSection div div.aem-Grid div.containerComp.parbase.aem-GridColumn div.containerComponent.smallPaddingTopDT.smallPaddingRightDT.smallPaddingBottomDT.smallPaddingLeftDT.smallPaddingTopMob.smallPaddingRightMob.noneBottomMob.smallPaddingLeftMob.gray div div.aem-Grid div.text.parbase.aem-GridColumn h3", text)).toContain("Featured articles");
    })
    .then((data) => {
    });
});

describe.each(dataset("data/dataset_5.json"))("test_05_post_plpXRb_YlO_param1_param2_param3_param4_aEs_aeId", (aeId, param, param1, param2, param3, sensor_data) => {
    it("test_05_post_plpXRb_YlO_param1_param2_param3_param4_aEs_aeId", () => {
        clearSession();

        // POST https://www.usbank.com/plpXRb/YlO/{param1}/{param2}/{param3}/{param4}/aEs/{aeId} (endp 5)
        const www_usbank_com = getHttpClient("https://www.usbank.com", authenticate);
        return www_usbank_com.fetch("/plpXRb/YlO/" + param + "/" + param1 + "/" + param2 + "/" + param3 + "/aEs/" + aeId, {
            method: "POST",
            headers: {
                "content-type": "application/json"
            },
            body: JSONBuild("data/payload_for_endp_5.json", {
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

it("test_53_get_site_map_html", () => {
    clearSession();

    // GET https://www.usbank.com/site-map.html (endp 53)
    const www_usbank_com = getHttpClient("https://www.usbank.com", authenticate);
    return www_usbank_com.fetch("/site-map.html")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("div#speedBumpModal div.modal-dialog div.modal-content div.modal-body.speedBump-body h3", text)).toContain("Leaving?");
        expect(CSSselect("html head title", text)).toContain("Site map | U.S. Bank");
    })
    .then((data) => {
    });
});

describe.each(dataset("data/dataset_54.json"))("test_54_get_svt_usbank_rpsfetchDisclosureContent", (disclosureTitles) => {
    it("test_54_get_svt_usbank_rpsfetchDisclosureContent", () => {
        clearSession();

        // GET https://www.usbank.com/svt/usbank/rpsfetchDisclosureContent (endp 54)
        const www_usbank_com = getHttpClient("https://www.usbank.com", authenticate);
        return www_usbank_com.fetch("/svt/usbank/rpsfetchDisclosureContent" + urlencode([["disclosureTitles", disclosureTitles]]), {
            headers: {
                "x-requested-with": "XMLHttpRequest"
            }
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
                path: "$[*].status",
                json: data
            })).toContain("success");
        });
    });
});

describe.each(dataset("data/dataset_6.json"))("test_06_post_svt_usbank_shield_fetchDisclosureContent", (businessLines, disclosureTitles) => {
    it("test_06_post_svt_usbank_shield_fetchDisclosureContent", () => {
        clearSession();

        // POST https://www.usbank.com/svt/usbank/shield/fetchDisclosureContent (endp 6)
        const www_usbank_com = getHttpClient("https://www.usbank.com", authenticate);
        return www_usbank_com.fetch("/svt/usbank/shield/fetchDisclosureContent", {
            method: "POST",
            headers: {
                "content-type": "application/x-www-form-urlencoded",
                "x-requested-with": "XMLHttpRequest"
            },
            body: new URLSearchParams({
                "businessLines": businessLines,
                "disclosureTitles": disclosureTitles
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
                path: "$[*].status",
                json: data
            })).toContain("success");
        });
    });
});

it("test_84_get_wealth_management_href", () => {
    clearSession();

    // GET https://www.usbank.com/index.html (endp 4)
    const www_usbank_com = getHttpClient("https://www.usbank.com", authenticate);
    return www_usbank_com.fetch("/index.html")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("html head title", text)).toContain("Consumer banking | Personal banking | U.S. Bank");
        const href = urlPart("/2", CSSselect("div#navigation-menu-dropdown div.menu-scrolls ul.menu-list.menu-primary li.menu-item.menu-primary-item ul.menu-list.menu-secondary li.menu-item.menu-secondary-item ul.menu-list.menu-tertiary li.menu-item.menu-tertiary-item a.menu-link.menu-tertiary-link[href] @href", response).text().trim());
    })
    .then((data) => {
        // GET https://www.usbank.com/wealth-management/{href} (endp 84)
        return www_usbank_com.fetch("/wealth-management/" + href)
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("section.pubIns div.bodyContent.container-fluid div.row div div.aem-Grid div.containerComp.parbase.aem-GridColumn div.containerComponent.mediumPaddingTopDT.largePaddingRightDT.mediumPaddingBottomDT.largePaddingLeftDT.smallPaddingTopMob.smallPaddingRightMob.smallPaddingBottomMob.smallPaddingLeftMob.gray div div.aem-Grid div.aem-GridColumn div.xf-content-height div.aem-Grid div.containerComp.parbase.aem-GridColumn div.containerComponent div div.aem-Grid div.aem-GridColumn div.xf-content-height div.aem-Grid div.containerComp.parbase.aem-GridColumn div.containerComponent div div.aem-Grid div.containerComp.parbase.aem-GridColumn--default--none.aem-GridColumn div.containerComponent.noneTopDT.mediumPaddingRightDT.noneBottomDT.noneleftDT.noneTopMob.noneRightMob.noneBottomMob.noneleftMob.transparent div div.aem-Grid div.parbase.aem-GridColumn div.usbTextImage div.textimage-text.text.largePaddingBottomSeparator div p", text)).toContain("No minimum investment required");
        })
        .then((data) => {
        });
    });
});
