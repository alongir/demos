const authenticate = require("./authentication");
const {CSSselect, clearSession, dataset, getHttpClient} = require("./up9lib");

it("test_02_get_apps_services_tags_https_account_chase_com_consumer_banking_seo", () => {
    clearSession();

    // GET https://www.chase.com/apps/services/tags/https/account.chase.com/consumer/banking/seo (endp 2)
    const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
    return www_chase_com.fetch("/apps/services/tags/https/account.chase.com/consumer/banking/seo")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_03_get_apps_services_tags_https_autofinance_chase_com_auto_finance_auto_loans", () => {
    clearSession();

    // GET https://www.chase.com/apps/services/tags/https/autofinance.chase.com/auto-finance/auto-loans (endp 3)
    const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
    return www_chase_com.fetch("/apps/services/tags/https/autofinance.chase.com/auto-finance/auto-loans")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_04_get_apps_services_tags_https_autofinance_chase_com_auto_finance_home", () => {
    clearSession();

    // GET https://www.chase.com/apps/services/tags/https/autofinance.chase.com/auto-finance/home (endp 4)
    const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
    return www_chase_com.fetch("/apps/services/tags/https/autofinance.chase.com/auto-finance/home")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_05_get_apps_services_tags_https_creditcards_chase_com_cash_back_credit_cards", () => {
    clearSession();

    // GET https://www.chase.com/apps/services/tags/https/creditcards.chase.com/cash-back-credit-cards (endp 5)
    const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
    return www_chase_com.fetch("/apps/services/tags/https/creditcards.chase.com/cash-back-credit-cards")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_06_get_apps_services_tags_https_creditcards_chase_com_cash_back_credit_cards_freedom_flex", () => {
    clearSession();

    // GET https://www.chase.com/apps/services/tags/https/creditcards.chase.com/cash-back-credit-cards/freedom/flex (endp 6)
    const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
    return www_chase_com.fetch("/apps/services/tags/https/creditcards.chase.com/cash-back-credit-cards/freedom/flex")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_07_get_apps_services_tags_https_locator_chase_com_", () => {
    clearSession();

    // GET https://www.chase.com/apps/services/tags/https/locator.chase.com/ (endp 7)
    const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
    return www_chase_com.fetch("/apps/services/tags/https/locator.chase.com/")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_08_get_apps_services_tags_https_locator_chase_com_es", () => {
    clearSession();

    // GET https://www.chase.com/apps/services/tags/https/locator.chase.com/es (endp 8)
    const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
    return www_chase_com.fetch("/apps/services/tags/https/locator.chase.com/es")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_09_get_apps_services_tags_https_locator_chase_com_search", () => {
    clearSession();

    // GET https://www.chase.com/apps/services/tags/https/locator.chase.com/search (endp 9)
    const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
    return www_chase_com.fetch("/apps/services/tags/https/locator.chase.com/search")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_10_get_apps_services_tags_https_personal_chase_com_personal_checking", () => {
    clearSession();

    // GET https://www.chase.com/apps/services/tags/https/personal.chase.com/personal/checking (endp 10)
    const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
    return www_chase_com.fetch("/apps/services/tags/https/personal.chase.com/personal/checking")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_11_get_apps_services_tags_https_www_chase_com_", () => {
    clearSession();

    // GET https://www.chase.com/apps/services/tags/https/www.chase.com/ (endp 11)
    const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
    return www_chase_com.fetch("/apps/services/tags/https/www.chase.com/")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_12_get_apps_services_tags_https_www_chase_com_content_chase_ux_en_structured_module_direct_to_dealer_primary_tool_liquid", () => {
    clearSession();

    // GET https://www.chase.com/apps/services/tags/https/www.chase.com/content/chase-ux/en/structured/module/direct-to-dealer/primary-tool-liquid (endp 12)
    const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
    return www_chase_com.fetch("/apps/services/tags/https/www.chase.com/content/chase-ux/en/structured/module/direct-to-dealer/primary-tool-liquid")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_13_get_apps_services_tags_https_www_chase_com_personal_investments_advisor", () => {
    clearSession();

    // GET https://www.chase.com/apps/services/tags/https/www.chase.com/personal/investments/advisor (endp 13)
    const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
    return www_chase_com.fetch("/apps/services/tags/https/www.chase.com/personal/investments/advisor")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_14_get_apps_services_tags_https_www_chase_com_personal_investments_advisor_contact_form", () => {
    clearSession();

    // GET https://www.chase.com/apps/services/tags/https/www.chase.com/personal/investments/advisor-contact-form (endp 14)
    const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
    return www_chase_com.fetch("/apps/services/tags/https/www.chase.com/personal/investments/advisor-contact-form")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_15_get_apps_services_tags_https_www_chase_com_personal_offers_marketplace", () => {
    clearSession();

    // GET https://www.chase.com/apps/services/tags/https/www.chase.com/personal/offers/marketplace (endp 15)
    const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
    return www_chase_com.fetch("/apps/services/tags/https/www.chase.com/personal/offers/marketplace")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

describe.each(dataset("data/dataset_22.json"))("test_22_get_content_chase_ux_en_structured_module_param1_index_param2_param3_module_html", (param, param1, param2) => {
    it("test_22_get_content_chase_ux_en_structured_module_param1_index_param2_param3_module_html", () => {
        clearSession();

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/index/{param2}/{param3}/module.html (endp 22)
        const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
        return www_chase_com.fetch("/content/chase-ux/en/structured/module/" + param + "/index/" + param1 + "/" + param2 + "/module.html", {
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
        });
    });
});

describe.each(dataset("data/dataset_23.json"))("test_23_get_content_chase_ux_en_structured_module_param1_prod_public_lucy_advisor_contact_form_mbox_param2_module_html", (param, param1) => {
    it("test_23_get_content_chase_ux_en_structured_module_param1_prod_public_lucy_advisor_contact_form_mbox_param2_module_html", () => {
        clearSession();

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/prod-public-lucy-advisor-contact-form-mbox/{param2}/module.html (endp 23)
        const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
        return www_chase_com.fetch("/content/chase-ux/en/structured/module/" + param + "/prod-public-lucy-advisor-contact-form-mbox/" + param1 + "/module.html", {
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
        });
    });
});

it("test_20_get_content_chase_ux_en_structured_module_direct_to_dealer_primary_tool_liquid", () => {
    clearSession();

    // GET https://www.chase.com/content/chase-ux/en/structured/module/direct-to-dealer/primary-tool-liquid (endp 20)
    const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
    return www_chase_com.fetch("/content/chase-ux/en/structured/module/direct-to-dealer/primary-tool-liquid")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("h2#speedbumpDefaultHeading", text)).toContain("You're now leaving Chase");
        expect(CSSselect("html head title", text)).toContain("primary-tool-liquid");
    })
    .then((data) => {
    });
});

describe.each(dataset("data/dataset_27.json"))("test_27_get_content_experience_fragments_microsites_lucy_form_lucylandingpage_master_param_root_html", (param) => {
    it("test_27_get_content_experience_fragments_microsites_lucy_form_lucylandingpage_master_param_root_html", () => {
        clearSession();

        // GET https://www.chase.com/content/experience-fragments/microsites/lucy-form/lucylandingpage/master/{param}/root.html (endp 27)
        const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
        return www_chase_com.fetch("/content/experience-fragments/microsites/lucy-form/lucylandingpage/master/" + param + "/root.html", {
            headers: {
                "x-requested-with": "XMLHttpRequest"
            }
        })
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("div.aem-Grid div.user-details-form.aem-GridColumn div.landingPageForm div.landingPageForm__header div.landingPageForm__header__message p a.chaseanalytics-track-link", text)).toContain("Sign in here");
        })
        .then((data) => {
        });
    });
});

it("test_29_get_personal_investments_advisor_contact_form", () => {
    clearSession();

    // GET https://www.chase.com/personal/investments/advisor-contact-form (endp 29)
    const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
    return www_chase_com.fetch("/personal/investments/advisor-contact-form")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("main#main h1.accessible-text", text)).toContain("J.P. Morgan Financial Advisor Contact Form | Chase");
        expect(CSSselect("html head title", text)).toContain("J.P. Morgan Financial Advisor Contact Form | Chase");
    })
    .then((data) => {
    });
});
