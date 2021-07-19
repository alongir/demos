const authenticate = require("./authentication");
const {CSSselect, clearSession, dataset, getHttpClient} = require("./up9lib");

it("test_002_get_apps_services_tags_https_account_chase_com_consumer_banking_seo", () => {
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

it("test_003_get_apps_services_tags_https_autofinance_chase_com_auto_finance_auto_loans", () => {
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

it("test_004_get_apps_services_tags_https_autofinance_chase_com_auto_finance_home", () => {
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

it("test_005_get_apps_services_tags_https_creditcards_chase_com_cash_back_credit_cards", () => {
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

it("test_006_get_apps_services_tags_https_creditcards_chase_com_cash_back_credit_cards_freedom_flex", () => {
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

it("test_007_get_apps_services_tags_https_locator_chase_com_", () => {
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

it("test_008_get_apps_services_tags_https_locator_chase_com_es", () => {
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

it("test_009_get_apps_services_tags_https_locator_chase_com_search", () => {
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

it("test_010_get_apps_services_tags_https_personal_chase_com_personal_checking", () => {
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

it("test_011_get_apps_services_tags_https_www_chase_com_", () => {
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

it("test_012_get_apps_services_tags_https_www_chase_com_content_chase_ux_en_structured_module_direct_to_dealer_primary_tool_liquid", () => {
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

it("test_013_get_apps_services_tags_https_www_chase_com_personal_investments_advisor", () => {
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

it("test_014_get_apps_services_tags_https_www_chase_com_personal_investments_advisor_contact_form", () => {
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

it("test_086_get_apps_services_tags_https_www_chase_com_personal_mortgage_mortgage_refinance", () => {
    clearSession();

    // GET https://www.chase.com/apps/services/tags/https/www.chase.com/personal/mortgage/mortgage-refinance (endp 86)
    const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
    return www_chase_com.fetch("/apps/services/tags/https/www.chase.com/personal/mortgage/mortgage-refinance")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_015_get_apps_services_tags_https_www_chase_com_personal_offers_marketplace", () => {
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

describe.each(dataset("data/dataset_90.json"))("test_090_get_content_chase_ux_en_structured_module_param_carousel_single_images_imageId_module_html", (imageId, param) => {
    it("test_090_get_content_chase_ux_en_structured_module_param_carousel_single_images_imageId_module_html", () => {
        clearSession();

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param}/carousel-single-images/{imageId}/module.html (endp 90)
        const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
        return www_chase_com.fetch("/content/chase-ux/en/structured/module/" + param + "/carousel-single-images/" + imageId + "/module.html", {
            headers: {
                "x-requested-with": "XMLHttpRequest"
            }
        })
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("div.carousel.carousel-version-b h2.carousel--title", text)).toContain("Choose what's right for you");
        })
        .then((data) => {
        });
    });
});

describe.each(dataset("data/dataset_17.json"))("test_017_get_content_chase_ux_en_structured_module_param_personal_investments_investmentId_iconId_module_html", (iconId, investmentId, param) => {
    it("test_017_get_content_chase_ux_en_structured_module_param_personal_investments_investmentId_iconId_module_html", () => {
        clearSession();

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param}/personal/investments/{investmentId}/{iconId}/module.html (endp 17)
        const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
        return www_chase_com.fetch("/content/chase-ux/en/structured/module/" + param + "/personal/investments/" + investmentId + "/" + iconId + "/module.html", {
            headers: {
                "x-requested-with": "XMLHttpRequest"
            }
        })
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("div.colctrl-section.neutral.zeroBottomPadding div.colctrl-section--inner.feature-container.row div.zeroBottomPadding div.bodysection.section div div div div h2", text)).toContain("Reach your financial goals with a J.P. Morgan Advisor");
        })
        .then((data) => {
        });
    });
});

describe.each(dataset("data/dataset_88.json"))("test_088_get_content_chase_ux_en_structured_module_param1_personal_mortgage_cta_call_online_comein_grey_param2_module_html", (param, param1) => {
    it("test_088_get_content_chase_ux_en_structured_module_param1_personal_mortgage_cta_call_online_comein_grey_param2_module_html", () => {
        clearSession();

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/personal/mortgage/cta/call-online-comein-grey/{param2}/module.html (endp 88)
        const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
        return www_chase_com.fetch("/content/chase-ux/en/structured/module/" + param + "/personal/mortgage/cta/call-online-comein-grey/" + param1 + "/module.html", {
            headers: {
                "x-requested-with": "XMLHttpRequest"
            }
        })
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("div.module-bucket.neutral div.feature-container.row div div h2", text)).toContain("Take the first step and get prequalified.");
        })
        .then((data) => {
        });
    });
});

describe.each(dataset("data/dataset_94.json"))("test_094_get_content_chase_ux_en_structured_module_param1_personal_mortgage_mortgage_refinance_hero_param2_module_html", (param, param1) => {
    it("test_094_get_content_chase_ux_en_structured_module_param1_personal_mortgage_mortgage_refinance_hero_param2_module_html", () => {
        clearSession();

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/personal/mortgage/mortgage-refinance-hero/{param2}/module.html (endp 94)
        const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
        return www_chase_com.fetch("/content/chase-ux/en/structured/module/" + param + "/personal/mortgage/mortgage-refinance-hero/" + param1 + "/module.html", {
            headers: {
                "x-requested-with": "XMLHttpRequest"
            }
        })
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("div.hero.short.white div.hero--text-container div.center.white h2", text)).toContain("Your refinance journey begins here");
        })
        .then((data) => {
        });
    });
});

describe.each(dataset("data/dataset_92.json"))("test_092_get_content_chase_ux_en_structured_module_param1_personal_mortgage_mortgage_refinance_hero_refinance_hero_param2_module_html", (param, param1) => {
    it("test_092_get_content_chase_ux_en_structured_module_param1_personal_mortgage_mortgage_refinance_hero_refinance_hero_param2_module_html", () => {
        clearSession();

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/personal/mortgage/mortgage-refinance-hero/refinance-hero/{param2}/module.html (endp 92)
        const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
        return www_chase_com.fetch("/content/chase-ux/en/structured/module/" + param + "/personal/mortgage/mortgage-refinance-hero/refinance-hero/" + param1 + "/module.html", {
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

describe.each(dataset("data/dataset_89.json"))("test_089_get_content_chase_ux_en_structured_module_param1_personal_mortgage_mortgage_refinance_mortgage_refinance_bucket_param2_module_html", (param, param1) => {
    it("test_089_get_content_chase_ux_en_structured_module_param1_personal_mortgage_mortgage_refinance_mortgage_refinance_bucket_param2_module_html", () => {
        clearSession();

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/personal/mortgage/mortgage-refinance/mortgage-refinance-bucket/{param2}/module.html (endp 89)
        const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
        return www_chase_com.fetch("/content/chase-ux/en/structured/module/" + param + "/personal/mortgage/mortgage-refinance/mortgage-refinance-bucket/" + param1 + "/module.html", {
            headers: {
                "x-requested-with": "XMLHttpRequest"
            }
        })
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("div.module-bucket.default div.feature-container.row div div h2", text)).toContain("Tools and tips to help you find the right loan for your needs");
        })
        .then((data) => {
        });
    });
});

describe.each(dataset("data/dataset_95.json"))("test_095_get_content_chase_ux_en_structured_module_param1_personal_mortgage_refinance_lp_param2_module_html", (param, param1) => {
    it("test_095_get_content_chase_ux_en_structured_module_param1_personal_mortgage_refinance_lp_param2_module_html", () => {
        clearSession();

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/personal/mortgage/refinance-lp/{param2}/module.html (endp 95)
        const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
        return www_chase_com.fetch("/content/chase-ux/en/structured/module/" + param + "/personal/mortgage/refinance-lp/" + param1 + "/module.html", {
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

describe.each(dataset("data/dataset_23.json"))("test_023_get_content_chase_ux_en_structured_module_param1_prod_public_lucy_advisor_contact_form_mbox_param2_module_html", (param, param1) => {
    it("test_023_get_content_chase_ux_en_structured_module_param1_prod_public_lucy_advisor_contact_form_mbox_param2_module_html", () => {
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

it("test_020_get_content_chase_ux_en_structured_module_direct_to_dealer_primary_tool_liquid", () => {
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

describe.each(dataset("data/dataset_27.json"))("test_027_get_content_experience_fragments_microsites_lucy_form_lucylandingpage_master_param_root_html", (param) => {
    it("test_027_get_content_experience_fragments_microsites_lucy_form_lucylandingpage_master_param_root_html", () => {
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

it("test_029_get_personal_investments_advisor_contact_form", () => {
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

it("test_096_get_personal_mortgage_mortgage_refinance", () => {
    clearSession();

    // GET https://www.chase.com/personal/mortgage/mortgage-refinance (endp 96)
    const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
    return www_chase_com.fetch("/personal/mortgage/mortgage-refinance")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("main#main h1.accessible-text", text)).toContain("Refinance your mortgage");
        expect(CSSselect("html head title", text)).toContain("Refinance your Mortgage | Refinance | Chase.com");
    })
    .then((data) => {
    });
});
