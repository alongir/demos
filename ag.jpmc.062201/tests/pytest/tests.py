from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_account_chase_com(unittest.TestCase):

    @json_dataset('data/dataset_58.json')
    @clear_session({'spanId': 58})
    def test_58_get_consumer_banking_seo(self, data_row):
        ssv_creativeid, = data_row

        # GET https://account.chase.com/consumer/banking/seo (endp 58)
        account_chase_com = get_http_client('https://account.chase.com', authenticate)
        qstr = '?' + urlencode([('jp_aid_a', ssv_creativeid), ('jp_aid_p', 'retail_checking_hp/tile')])
        resp = account_chase_com.get('/consumer/banking/seo' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('div#leaving-modal div.leaving-modal-center div.leaving-modal-title h1', expected_value="You're Now Leaving Chase")

    @json_dataset('data/dataset_59.json')
    @clear_session({'spanId': 59})
    def test_59_post_consumer_banking_seo(self, data_row):
        Payload_ContactPoint, Payload_ProductSelection, form_build_id, referingURL, ssv_creativeid = data_row

        # POST https://account.chase.com/consumer/banking/seo (endp 59)
        account_chase_com = get_http_client('https://account.chase.com', authenticate)
        qstr = '?' + urlencode([('jp_aid_a', ssv_creativeid), ('jp_aid_p', 'retail_checking_hp/tile')])
        resp = account_chase_com.post('/consumer/banking/seo' + qstr, data=[('Payload_ContactPoint', Payload_ContactPoint), ('Payload_IsDaoWithEmailSubmission', 'false'), ('Payload_IsEmailSubmission', 'true'), ('Payload_ProductSelection', Payload_ProductSelection), ('Payload_ValidationMsg', ''), ('device_type', 'Desktop'), ('form_build_id', form_build_id), ('form_id', 'email_my_coupon'), ('gclid', ''), ('optimizelyID', 'default'), ('prd_link', ''), ('referingURL', referingURL), ('v1stCookie', '')], headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)


@data_driven_tests
class Tests_autofinance_chase_com(unittest.TestCase):

    @json_dataset('data/dataset_82.json')
    @clear_session({'spanId': 82})
    def test_82_get_auto_finance_auto_loans(self, data_row):
        offercode, = data_row

        # GET https://autofinance.chase.com/auto-finance/auto-loans (endp 82)
        autofinance_chase_com = get_http_client('https://autofinance.chase.com', authenticate)
        qstr = '?' + urlencode([('offercode', offercode)])
        resp = autofinance_chase_com.get('/auto-finance/auto-loans' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('div#masthead div.finance-masthead.bkgd-image div.masthead-header.container div.row div.headline.text-left h1', expected_value='Finance a car')

    @json_dataset('data/dataset_83.json')
    @clear_session({'spanId': 83})
    def test_83_get_auto_finance_home(self, data_row):
        offercode, = data_row

        # GET https://autofinance.chase.com/auto-finance/home (endp 83)
        autofinance_chase_com = get_http_client('https://autofinance.chase.com', authenticate)
        qstr = '?' + urlencode([('offercode', offercode)])
        resp = autofinance_chase_com.get('/auto-finance/home' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('div#masthead div.home-masthead.bkgd-image div.masthead-header.container div.row div.headline.text-left h1', expected_value="It's your road, choose where to go.")


@data_driven_tests
class Tests_creditcards_chase_com(unittest.TestCase):

    @json_dataset('data/dataset_66.json')
    @clear_session({'spanId': 66})
    def test_66_get_cash_back_credit_cards(self, data_row):
        CELL, = data_row

        # GET https://creditcards.chase.com/cash-back-credit-cards (endp 66)
        creditcards_chase_com = get_http_client('https://creditcards.chase.com', authenticate)
        qstr = '?' + urlencode([('CELL', CELL), ('jp_ltg', 'chsecate_cashback')])
        resp = creditcards_chase_com.get('/cash-back-credit-cards' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('div#jsEnabled div.compatibility footer.footer div.row.content-section div.d-none.container div.section h3.placeholder-hidden', expected_value='Credit Cards')
        resp.assert_cssselect('html head title', expected_value='Compare Cash Back Credit Cards | Chase')

    @json_dataset('data/dataset_65.json')
    @clear_session({'spanId': 65})
    def test_65_get_cash_back_credit_cards_freedom_flex(self, data_row):
        CELL, = data_row

        # GET https://creditcards.chase.com/cash-back-credit-cards/freedom/flex (endp 65)
        creditcards_chase_com = get_http_client('https://creditcards.chase.com', authenticate)
        qstr = '?' + urlencode([('CELL', CELL)])
        resp = creditcards_chase_com.get('/cash-back-credit-cards/freedom/flex' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('div#leftPanel div.left-rail-inner.clearfix div.no-padding.left-rail-header.freedomflex h1.card-title sup.sm-fix', expected_value='SM')
        resp.assert_cssselect('html head title', expected_value='Chase Freedom Flex Credit Card | Chase.com')


@data_driven_tests
class Tests_locator_chase_com(unittest.TestCase):

    @clear_session({'spanId': 73})
    def test_73_get_(self):
        # GET https://locator.chase.com/ (endp 73)
        locator_chase_com = get_http_client('https://locator.chase.com', authenticate)
        qstr = '?' + urlencode([('locale', 'en_US')])
        resp = locator_chase_com.get('/' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('div#hero-label h1', expected_value='Find a Chase ATM or branch near you')
        resp.assert_cssselect('html head title', expected_value='Find a Chase ATM or branch near you | Chase Bank')

    @clear_session({'spanId': 74})
    def test_74_get_adspace(self):
        # GET https://locator.chase.com/adspace (endp 74)
        locator_chase_com = get_http_client('https://locator.chase.com', authenticate)
        resp = locator_chase_com.get('/adspace')
        resp.assert_status_code(200)


@data_driven_tests
class Tests_midas_chase_com(unittest.TestCase):

    @json_dataset('data/dataset_33.json')
    @clear_session({'spanId': 33})
    def test_33_get_stream_click(self, data_row):
        ssv_creativeid, ssv_midas_id, ssv_random, ssv_transid, ssv_v1st = data_row

        # GET https://midas.chase.com/stream/click (endp 33)
        midas_chase_com = get_http_client('https://midas.chase.com', authenticate)
        qstr = '?' + urlencode([('ssv_channel', 'web'), ('ssv_creativeid', ssv_creativeid), ('ssv_locale', 'en_us'), ('ssv_midas_id', ssv_midas_id), ('ssv_random', ssv_random), ('ssv_transid', ssv_transid), ('ssv_v1st', ssv_v1st)])
        resp = midas_chase_com.get('/stream/click' + qstr)
        resp.assert_status_code(200)

    @json_dataset('data/dataset_34.json')
    @clear_session({'spanId': 34})
    def test_34_get_stream_tag(self, data_row):
        pageID, ssv_eid, ssv_tmc, ssv_v1st = data_row

        # GET https://midas.chase.com/stream/tag (endp 34)
        midas_chase_com = get_http_client('https://midas.chase.com', authenticate)
        qstr = '?' + urlencode([('pageID', pageID), ('ssv_eci', ''), ('ssv_eid', ssv_eid), ('ssv_pfid', ''), ('ssv_productid', ''), ('ssv_src', ''), ('ssv_tmc', ssv_tmc), ('ssv_v1st', ssv_v1st)])
        resp = midas_chase_com.get('/stream/tag' + qstr)
        resp.assert_status_code(200)

    @json_dataset('data/dataset_35.json')
    @clear_session({'spanId': 35})
    def test_35_get_stream_view(self, data_row):
        ssv_creativeid, ssv_locale, ssv_midas_id, ssv_v1st = data_row

        # GET https://midas.chase.com/stream/view (endp 35)
        midas_chase_com = get_http_client('https://midas.chase.com', authenticate)
        qstr = '?' + urlencode([('ssv_channel', 'web'), ('ssv_creativeid', ssv_creativeid), ('ssv_locale', ssv_locale), ('ssv_midas_id', ssv_midas_id), ('ssv_pageLayout', 'prospect_a'), ('ssv_random', str(random.randint(220, 977))), ('ssv_transid', str(random.randint(-8655682384752228023, 2808894671955032376))), ('ssv_v1st', ssv_v1st)])
        resp = midas_chase_com.get('/stream/view' + qstr)
        resp.assert_status_code(200)


@data_driven_tests
class Tests_secure01b_chase_com(unittest.TestCase):

    @json_dataset('data/dataset_42.json')
    @clear_session({'spanId': 42})
    def test_42_post_events_eventId(self, data_row):
        app, eventId, modifiedSince, referer, svrid, visitID = data_row

        # POST https://secure01b.chase.com/events/{eventId} (endp 42)
        secure01b_chase_com = get_http_client('https://secure01b.chase.com', authenticate)
        qstr = '?' + urlencode([('app', app), ('flavor', 'post'), ('modifiedSince', modifiedSince), ('referer', referer), ('session', str(get_data_from_cookie('dtCookie'))), ('svrid', svrid), ('type', 'js'), ('visitID', visitID)])
        with open('data/payload_for_endp_42.txt', 'r') as payload_file:
            payload = payload_file.read()
        resp = secure01b_chase_com.post(f'/events/{eventId}' + qstr, data=payload, headers=dict([('content-type', 'text/plain')]))
        resp.assert_status_code(200)

    @json_dataset('data/dataset_41.json')
    @clear_session({'spanId': 41})
    def test_41_post_events_analytics_public_v1_events_raw_(self, data_row):
        adobeData, browserRes, colorDepth, currentURL, javaScriptVer, referrerURL, screenRes, server_offset, site_, tz_offset, version, visitor, visitorId = data_row

        # POST https://secure01b.chase.com/events/analytics/public/v1/events/raw/ (endp 41)
        secure01b_chase_com = get_http_client('https://secure01b.chase.com', authenticate)
        with open('data/payload_for_endp_41.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.events[*].app.version', version)
        apply_into_json(json_payload, '$.events[*].device.browserRes', browserRes)
        apply_into_json(json_payload, '$.events[*].device.colorDepth', colorDepth)
        apply_into_json(json_payload, '$.events[*].device.javaScriptVer', javaScriptVer)
        apply_into_json(json_payload, '$.events[*].device.screenRes', screenRes)
        apply_into_json(json_payload, '$.events[*].location.server_offset', server_offset)
        apply_into_json(json_payload, '$.events[*].location.tz_offset', tz_offset)
        apply_into_json(json_payload, '$.events[*].payload.data.referrerURL', referrerURL)
        apply_into_json(json_payload, '$.events[*].payload.timestamp', int(int(time.time() * 1000)))
        apply_into_json(json_payload, '$.events[*].screen.currentURL', currentURL)
        apply_into_json(json_payload, '$.events[*].site', site_)
        apply_into_json(json_payload, '$.events[*].visitor.*', visitor)
        apply_into_json(json_payload, '$.events[*].visitor.adobeData', adobeData)
        apply_into_json(json_payload, '$.events[*].visitor.visitorId', visitorId)
        resp = secure01b_chase_com.post('/events/analytics/public/v1/events/raw/', json=json_payload)
        resp.assert_status_code(200)

    # authentication-related test
    @json_dataset('data/dataset_43.json')
    @clear_session({'spanId': 43})
    def test_43_get_web_auth_logonbox(self, data_row):
        fromOrigin, = data_row

        # GET https://secure01b.chase.com/web/auth/logonbox (endp 43)
        secure01b_chase_com = get_http_client('https://secure01b.chase.com', dummy_auth)
        qstr = '?' + urlencode([('fromOrigin', fromOrigin), ('lang', 'en')])
        resp = secure01b_chase_com.get('/web/auth/logonbox' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('html head title', expected_value='Chase Account login')


@data_driven_tests
class Tests_secure03b_chase_com(unittest.TestCase):

    @json_dataset('data/dataset_71.json')
    @clear_session({'spanId': 71})
    def test_71_post_events_eventId(self, data_row):
        app, eventId, modifiedSince, referer, session, svrid, visitID = data_row

        # POST https://secure03b.chase.com/events/{eventId} (endp 71)
        secure03b_chase_com = get_http_client('https://secure03b.chase.com', authenticate)
        qstr = '?' + urlencode([('app', app), ('flavor', 'post'), ('modifiedSince', modifiedSince), ('referer', referer), ('session', session), ('svrid', svrid), ('type', 'js'), ('visitID', visitID)])
        with open('data/payload_for_endp_71.txt', 'r') as payload_file:
            payload = payload_file.read()
        resp = secure03b_chase_com.post(f'/events/{eventId}' + qstr, data=payload, headers=dict([('content-type', 'text/plain')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 70})
    def test_70_post_events_analytics_public_v1_events_raw_(self):
        # POST https://secure03b.chase.com/events/analytics/public/v1/events/raw/ (endp 70)
        secure03b_chase_com = get_http_client('https://secure03b.chase.com', authenticate)
        with open('data/payload_for_endp_70.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        resp = secure03b_chase_com.post('/events/analytics/public/v1/events/raw/', json=json_payload)
        resp.assert_status_code(200)

    # authentication-related test
    @json_dataset('data/dataset_72.json')
    @clear_session({'spanId': 72})
    def test_72_get_web_auth_logonbox(self, data_row):
        CELL, = data_row

        # GET https://creditcards.chase.com/cash-back-credit-cards/freedom/flex (endp 65)
        creditcards_chase_com = get_http_client('https://creditcards.chase.com', dummy_auth)
        qstr = '?' + urlencode([('CELL', CELL)])
        resp = creditcards_chase_com.get('/cash-back-credit-cards/freedom/flex' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('div#leftPanel div.left-rail-inner.clearfix div.no-padding.left-rail-header.freedomflex h1.card-title sup.sm-fix', expected_value='SM')
        resp.assert_cssselect('html head title', expected_value='Chase Freedom Flex Credit Card | Chase.com')
        fromOrigin = cssselect('ul#hamNav-links-general li a.chaseanalytics-track-link[href] @href', resp)

        # GET https://secure03b.chase.com/web/auth/logonbox (endp 72)
        secure03b_chase_com = get_http_client('https://secure03b.chase.com', dummy_auth)
        qstr = '?' + urlencode([('fromOrigin', fromOrigin), ('lang', 'en'), ('navKey', 'reviewCreditCardOffers')])
        resp = secure03b_chase_com.get('/web/auth/logonbox' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('html head title', expected_value='Chase Account login')


@data_driven_tests
class Tests_secure05b_chase_com(unittest.TestCase):

    @json_dataset('data/dataset_63.json')
    @clear_session({'spanId': 63})
    def test_63_post_events_eventId(self, data_row):
        app, eventId, modifiedSince, referer, session, svrid, visitID = data_row

        # POST https://secure05b.chase.com/events/{eventId} (endp 63)
        secure05b_chase_com = get_http_client('https://secure05b.chase.com', authenticate)
        qstr = '?' + urlencode([('app', app), ('flavor', 'post'), ('modifiedSince', modifiedSince), ('referer', referer), ('session', session), ('svrid', svrid), ('type', 'js'), ('visitID', visitID)])
        with open('data/payload_for_endp_63.txt', 'r') as payload_file:
            payload = payload_file.read()
        resp = secure05b_chase_com.post(f'/events/{eventId}' + qstr, data=payload, headers=dict([('content-type', 'text/plain')]))
        resp.assert_status_code(200)

    @json_dataset('data/dataset_62.json')
    @clear_session({'spanId': 62})
    def test_62_post_events_analytics_public_v1_events_raw_(self, data_row):
        adobeData, browserRes, colorDepth, currentURL, javaScriptVer, screenRes, server_offset, site_, tz_offset, version, visitor, visitorId = data_row

        # GET https://personal.chase.com/personal/checking (endp 56)
        personal_chase_com = get_http_client('https://personal.chase.com', authenticate)
        resp = personal_chase_com.get('/personal/checking')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#jsEnabled div.generic-modal.card-modal div.modal-dialog div.modal-content div.row.modal-heading h3 sup.sm-fix', expected_value='SM')
        resp.assert_cssselect('html head title', expected_value='Chase Checking Accounts: Compare & Apply Today | Chase')
        referrerURL = cssselect('header#header div.bg-gradient div.bg-solid.bluebg div.inner div.container div.row div.header-flex.header-center.no-gutters.text-center.text-nowrap a.chaseanalytics-track-link[href] @href', resp)

        # POST https://secure05b.chase.com/events/analytics/public/v1/events/raw/ (endp 62)
        secure05b_chase_com = get_http_client('https://secure05b.chase.com', authenticate)
        with open('data/payload_for_endp_62.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.events[*].app.version', version)
        apply_into_json(json_payload, '$.events[*].device.browserRes', browserRes)
        apply_into_json(json_payload, '$.events[*].device.colorDepth', colorDepth)
        apply_into_json(json_payload, '$.events[*].device.javaScriptVer', javaScriptVer)
        apply_into_json(json_payload, '$.events[*].device.screenRes', screenRes)
        apply_into_json(json_payload, '$.events[*].location.server_offset', server_offset)
        apply_into_json(json_payload, '$.events[*].location.tz_offset', tz_offset)
        apply_into_json(json_payload, '$.events[*].payload.data.referrerURL', referrerURL)
        apply_into_json(json_payload, '$.events[*].payload.timestamp', int(int(time.time() * 1000)))
        apply_into_json(json_payload, '$.events[*].screen.currentURL', currentURL)
        apply_into_json(json_payload, '$.events[*].site', site_)
        apply_into_json(json_payload, '$.events[*].visitor.*', visitor)
        apply_into_json(json_payload, '$.events[*].visitor.adobeData', adobeData)
        apply_into_json(json_payload, '$.events[*].visitor.visitorId', visitorId)
        resp = secure05b_chase_com.post('/events/analytics/public/v1/events/raw/', json=json_payload)
        resp.assert_status_code(200)

    # authentication-related test
    @json_dataset('data/dataset_64.json')
    @clear_session({'spanId': 64})
    def test_64_get_web_auth_logonbox(self, data_row):
        fromOrigin, = data_row

        # GET https://secure05b.chase.com/web/auth/logonbox (endp 64)
        secure05b_chase_com = get_http_client('https://secure05b.chase.com', dummy_auth)
        qstr = '?' + urlencode([('fromOrigin', fromOrigin), ('lang', 'en')])
        resp = secure05b_chase_com.get('/web/auth/logonbox' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('html head title', expected_value='Chase Account login')


@data_driven_tests
class Tests_secure07a_chase_com(unittest.TestCase):

    @json_dataset('data/dataset_80.json')
    @clear_session({'spanId': 80})
    def test_80_post_events_eventId(self, data_row):
        app, eventId, modifiedSince, referer, session, svrid, visitID = data_row

        # POST https://secure07a.chase.com/events/{eventId} (endp 80)
        secure07a_chase_com = get_http_client('https://secure07a.chase.com', authenticate)
        qstr = '?' + urlencode([('app', app), ('flavor', 'post'), ('modifiedSince', modifiedSince), ('referer', referer), ('session', session), ('svrid', svrid), ('type', 'js'), ('visitID', visitID)])
        with open('data/payload_for_endp_80.txt', 'r') as payload_file:
            payload = payload_file.read()
        resp = secure07a_chase_com.post(f'/events/{eventId}' + qstr, data=payload, headers=dict([('content-type', 'text/plain')]))
        resp.assert_status_code(200)

    @json_dataset('data/dataset_79.json')
    @clear_session({'spanId': 79})
    def test_79_post_events_analytics_public_v1_events_raw_(self, data_row):
        adobeData, browserRes, colorDepth, currentURL, javaScriptVer, q, referrerURL, screenRes, server_offset, site_, tz_offset, version, visitor, visitorId = data_row

        # GET https://locator.chase.com/search (endp 77)
        locator_chase_com = get_http_client('https://locator.chase.com', authenticate)
        qstr = '?' + urlencode([('l', 'en'), ('q', q)])
        resp = locator_chase_com.get('/search' + qstr, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.response.entities[*].profile.address.region', expected_value='CA')
        metaId = jsonpath('$.response.entities[*].profile.*[*]', resp)

        # GET https://locator.chase.com/atmsearch (endp 75)
        qstr = '?' + urlencode([('metaId', metaId)])
        resp = locator_chase_com.get('/atmsearch' + qstr, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(200)
        redirectScreen = jsonpath('$.schema.alternateWebsites.archived', resp)

        # POST https://secure07a.chase.com/events/analytics/public/v1/events/raw/ (endp 79)
        secure07a_chase_com = get_http_client('https://secure07a.chase.com', authenticate)
        with open('data/payload_for_endp_79.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.events[*].app.version', version)
        apply_into_json(json_payload, '$.events[*].device.browserRes', browserRes)
        apply_into_json(json_payload, '$.events[*].device.colorDepth', colorDepth)
        apply_into_json(json_payload, '$.events[*].device.javaScriptVer', javaScriptVer)
        apply_into_json(json_payload, '$.events[*].device.screenRes', screenRes)
        apply_into_json(json_payload, '$.events[*].location.server_offset', server_offset)
        apply_into_json(json_payload, '$.events[*].location.tz_offset', tz_offset)
        apply_into_json(json_payload, '$.events[*].payload.data.referrerURL', referrerURL)
        apply_into_json(json_payload, '$.events[*].payload.timestamp', int(int(time.time() * 1000)))
        apply_into_json(json_payload, '$.events[*].screen.currentURL', currentURL)
        apply_into_json(json_payload, '$.events[*].site', site_)
        apply_into_json(json_payload, '$.events[*].visitor.*', visitor)
        apply_into_json(json_payload, '$.events[*].visitor.adobeData', adobeData)
        apply_into_json(json_payload, '$.events[*].visitor.visitorId', visitorId)
        resp = secure07a_chase_com.post('/events/analytics/public/v1/events/raw/', json=json_payload)
        resp.assert_status_code(200)

    # authentication-related test
    @json_dataset('data/dataset_81.json')
    @clear_session({'spanId': 81})
    def test_81_get_web_auth_logonbox(self, data_row):
        q, = data_row

        # GET https://locator.chase.com/search (endp 76)
        locator_chase_com = get_http_client('https://locator.chase.com', dummy_auth)
        qstr = '?' + urlencode([('q', q)])
        resp = locator_chase_com.get('/search' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('div#js-locator div.Locator-content h1.Locator-title.Heading--locator.js-Locator-title', expected_value='Find a Chase ATM or branch near you')
        resp.assert_cssselect('html head title', expected_value='Branches and ATMs | Chase Bank')
        fromOrigin = cssselect('a#brand-logo[href] @href', resp)

        # GET https://secure07a.chase.com/web/auth/logonbox (endp 81)
        secure07a_chase_com = get_http_client('https://secure07a.chase.com', dummy_auth)
        qstr = '?' + urlencode([('fromOrigin', fromOrigin), ('lang', 'en')])
        resp = secure07a_chase_com.get('/web/auth/logonbox' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('html head title', expected_value='Chase Account login')


@data_driven_tests
class Tests_sites_chase_com(unittest.TestCase):

    @json_dataset('data/dataset_36.json')
    @clear_session({'spanId': 36})
    def test_36_get_content_Creatives_Public_Heroes_heroeId_March_cid(self, data_row):
        cid, heroeId = data_row

        # GET https://sites.chase.com/content/Creatives/Public/Heroes/{heroeId}/March/{cid} (endp 36)
        sites_chase_com = get_http_client('https://sites.chase.com', authenticate)
        resp = sites_chase_com.get(f'/content/Creatives/Public/Heroes/{heroeId}/March/{cid}')
        resp.assert_status_code(200)

    @json_dataset('data/dataset_37.json')
    @clear_session({'spanId': 37})
    def test_37_get_content_Creatives_Public_Tiles_tileId_March_cid(self, data_row):
        cid, tileId = data_row

        # GET https://sites.chase.com/content/Creatives/Public/Tiles/{tileId}/March/{cid} (endp 37)
        sites_chase_com = get_http_client('https://sites.chase.com', authenticate)
        resp = sites_chase_com.get(f'/content/Creatives/Public/Tiles/{tileId}/March/{cid}')
        resp.assert_status_code(200)

    @json_dataset('data/dataset_38.json')
    @clear_session({'spanId': 38})
    def test_38_get_content_Creatives_Public_Triplets_tripletId_July_cid(self, data_row):
        cid, tripletId = data_row

        # GET https://sites.chase.com/content/Creatives/Public/Triplets/{tripletId}/July/{cid} (endp 38)
        sites_chase_com = get_http_client('https://sites.chase.com', authenticate)
        resp = sites_chase_com.get(f'/content/Creatives/Public/Triplets/{tripletId}/July/{cid}')
        resp.assert_status_code(200)

    @json_dataset('data/dataset_40.json')
    @clear_session({'spanId': 40})
    def test_40_get_content_Creatives_Public_Triplets_tripletId_May_cid(self, data_row):
        cid, tripletId = data_row

        # GET https://sites.chase.com/content/Creatives/Public/Triplets/{tripletId}/May/{cid} (endp 40)
        sites_chase_com = get_http_client('https://sites.chase.com', authenticate)
        resp = sites_chase_com.get(f'/content/Creatives/Public/Triplets/{tripletId}/May/{cid}')
        resp.assert_status_code(200)

    @json_dataset('data/dataset_39.json')
    @clear_session({'spanId': 39})
    def test_39_get_content_Creatives_Public_Triplets_tripletId_November_cid(self, data_row):
        cid, tripletId = data_row

        # GET https://sites.chase.com/content/Creatives/Public/Triplets/{tripletId}/November/{cid} (endp 39)
        sites_chase_com = get_http_client('https://sites.chase.com', authenticate)
        resp = sites_chase_com.get(f'/content/Creatives/Public/Triplets/{tripletId}/November/{cid}')
        resp.assert_status_code(200)


@data_driven_tests
class Tests_target_chase_com(unittest.TestCase):

    @json_dataset('data/dataset_31.json')
    @clear_session({'spanId': 31})
    def test_31_post_rest_v1_delivery(self, data_row):
        client, colorDepth, height, height1, locationHint, logging_, marketingCloudVisitorId, mboxName, name, pageTitle, profileParameters, q, requestId, sessionId, timeOffsetInMinutes, tntId, token_, userAgent, version, webGLRenderer, width, width1 = data_row

        # GET https://www.chase.com/personal/investments/advisor (endp 28)
        www_chase_com = get_http_client('https://www.chase.com', authenticate)
        resp = www_chase_com.get('/personal/investments/advisor')
        resp.assert_status_code(200)
        resp.assert_cssselect('main#main h1.accessible-text', expected_value='J.P. Morgan Financial Advisors')
        resp.assert_cssselect('html head title', expected_value='Connect with a J.P. Morgan Financial Advisor | Chase.com')

        # GET https://locator.chase.com/search (endp 76)
        locator_chase_com = get_http_client('https://locator.chase.com', authenticate)
        qstr = '?' + urlencode([('q', q)])
        resp = locator_chase_com.get('/search' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('div#js-locator div.Locator-content h1.Locator-title.Heading--locator.js-Locator-title', expected_value='Find a Chase ATM or branch near you')
        resp.assert_cssselect('html head title', expected_value='Branches and ATMs | Chase Bank')
        referringUrl = cssselect('header#Header div.Header-wrapper.l-container div.Header-mainRow div.Header-left div div.js-focustrap div div a.Header-expandedLink--atm.Text--expanded[href] @href', resp)
        url = cssselect('header#Header div.Header-wrapper.l-container div.Header-mainRow div.Header-left div div.js-focustrap div a.Text--expanded[href] @href', resp)

        # POST https://target.chase.com/rest/v1/delivery (endp 31)
        target_chase_com = get_http_client('https://target.chase.com', authenticate)
        qstr = '?' + urlencode([('client', client), ('sessionId', sessionId), ('version', version)])
        with open('data/payload_for_endp_31.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.context.address.referringUrl', referringUrl)
        apply_into_json(json_payload, '$.context.address.url', url)
        apply_into_json(json_payload, '$.context.browser.webGLRenderer', webGLRenderer)
        apply_into_json(json_payload, '$.context.screen.colorDepth', colorDepth)
        apply_into_json(json_payload, '$.context.screen.height', height)
        apply_into_json(json_payload, '$.context.screen.width', width)
        apply_into_json(json_payload, '$.context.timeOffsetInMinutes', timeOffsetInMinutes)
        apply_into_json(json_payload, '$.context.userAgent', userAgent)
        apply_into_json(json_payload, '$.context.window.height', height1)
        apply_into_json(json_payload, '$.context.window.width', width1)
        apply_into_json(json_payload, '$.execute.mboxes[*].name', name)
        apply_into_json(json_payload, '$.execute.mboxes[*].parameters.mboxName', mboxName)
        apply_into_json(json_payload, '$.execute.mboxes[*].parameters.pageTitle', pageTitle)
        apply_into_json(json_payload, '$.execute.mboxes[*].profileParameters.*', profileParameters)
        apply_into_json(json_payload, '$.experienceCloud.analytics.logging', logging_)
        apply_into_json(json_payload, '$.experienceCloud.audienceManager.locationHint', locationHint)
        apply_into_json(json_payload, '$.id.marketingCloudVisitorId', marketingCloudVisitorId)
        apply_into_json(json_payload, '$.id.tntId', tntId)
        apply_into_json(json_payload, '$.property.token', token_)
        apply_into_json(json_payload, '$.requestId', requestId)
        resp = target_chase_com.post('/rest/v1/delivery' + qstr, json=json_payload)
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.execute.mboxes[*].analytics.payload.pe', expected_value='tnt')


@data_driven_tests
class Tests_www_chase_com(unittest.TestCase):

    @clear_session({'spanId': 2})
    def test_02_get_apps_services_tags_https_account_chase_com_consumer_banking_seo(self):
        # GET https://www.chase.com/apps/services/tags/https/account.chase.com/consumer/banking/seo (endp 2)
        www_chase_com = get_http_client('https://www.chase.com', authenticate)
        resp = www_chase_com.get('/apps/services/tags/https/account.chase.com/consumer/banking/seo')
        resp.assert_status_code(200)

    @clear_session({'spanId': 3})
    def test_03_get_apps_services_tags_https_autofinance_chase_com_auto_finance_auto_loans(self):
        # GET https://www.chase.com/apps/services/tags/https/autofinance.chase.com/auto-finance/auto-loans (endp 3)
        www_chase_com = get_http_client('https://www.chase.com', authenticate)
        resp = www_chase_com.get('/apps/services/tags/https/autofinance.chase.com/auto-finance/auto-loans')
        resp.assert_status_code(200)

    @clear_session({'spanId': 4})
    def test_04_get_apps_services_tags_https_autofinance_chase_com_auto_finance_home(self):
        # GET https://www.chase.com/apps/services/tags/https/autofinance.chase.com/auto-finance/home (endp 4)
        www_chase_com = get_http_client('https://www.chase.com', authenticate)
        resp = www_chase_com.get('/apps/services/tags/https/autofinance.chase.com/auto-finance/home')
        resp.assert_status_code(200)

    @clear_session({'spanId': 5})
    def test_05_get_apps_services_tags_https_creditcards_chase_com_cash_back_credit_cards(self):
        # GET https://www.chase.com/apps/services/tags/https/creditcards.chase.com/cash-back-credit-cards (endp 5)
        www_chase_com = get_http_client('https://www.chase.com', authenticate)
        resp = www_chase_com.get('/apps/services/tags/https/creditcards.chase.com/cash-back-credit-cards')
        resp.assert_status_code(200)

    @clear_session({'spanId': 6})
    def test_06_get_apps_services_tags_https_creditcards_chase_com_cash_back_credit_cards_freedom_flex(self):
        # GET https://www.chase.com/apps/services/tags/https/creditcards.chase.com/cash-back-credit-cards/freedom/flex (endp 6)
        www_chase_com = get_http_client('https://www.chase.com', authenticate)
        resp = www_chase_com.get('/apps/services/tags/https/creditcards.chase.com/cash-back-credit-cards/freedom/flex')
        resp.assert_status_code(200)

    @clear_session({'spanId': 7})
    def test_07_get_apps_services_tags_https_locator_chase_com_(self):
        # GET https://www.chase.com/apps/services/tags/https/locator.chase.com/ (endp 7)
        www_chase_com = get_http_client('https://www.chase.com', authenticate)
        resp = www_chase_com.get('/apps/services/tags/https/locator.chase.com/')
        resp.assert_status_code(200)

    @clear_session({'spanId': 8})
    def test_08_get_apps_services_tags_https_locator_chase_com_es(self):
        # GET https://www.chase.com/apps/services/tags/https/locator.chase.com/es (endp 8)
        www_chase_com = get_http_client('https://www.chase.com', authenticate)
        resp = www_chase_com.get('/apps/services/tags/https/locator.chase.com/es')
        resp.assert_status_code(200)

    @clear_session({'spanId': 9})
    def test_09_get_apps_services_tags_https_locator_chase_com_search(self):
        # GET https://www.chase.com/apps/services/tags/https/locator.chase.com/search (endp 9)
        www_chase_com = get_http_client('https://www.chase.com', authenticate)
        resp = www_chase_com.get('/apps/services/tags/https/locator.chase.com/search')
        resp.assert_status_code(200)

    @clear_session({'spanId': 10})
    def test_10_get_apps_services_tags_https_personal_chase_com_personal_checking(self):
        # GET https://www.chase.com/apps/services/tags/https/personal.chase.com/personal/checking (endp 10)
        www_chase_com = get_http_client('https://www.chase.com', authenticate)
        resp = www_chase_com.get('/apps/services/tags/https/personal.chase.com/personal/checking')
        resp.assert_status_code(200)

    @clear_session({'spanId': 11})
    def test_11_get_apps_services_tags_https_www_chase_com_(self):
        # GET https://www.chase.com/apps/services/tags/https/www.chase.com/ (endp 11)
        www_chase_com = get_http_client('https://www.chase.com', authenticate)
        resp = www_chase_com.get('/apps/services/tags/https/www.chase.com/')
        resp.assert_status_code(200)

    @clear_session({'spanId': 12})
    def test_12_get_apps_services_tags_https_www_chase_com_content_chase_ux_en_structured_module_direct_to_dealer_primary_tool_liquid(self):
        # GET https://www.chase.com/apps/services/tags/https/www.chase.com/content/chase-ux/en/structured/module/direct-to-dealer/primary-tool-liquid (endp 12)
        www_chase_com = get_http_client('https://www.chase.com', authenticate)
        resp = www_chase_com.get('/apps/services/tags/https/www.chase.com/content/chase-ux/en/structured/module/direct-to-dealer/primary-tool-liquid')
        resp.assert_status_code(200)

    @clear_session({'spanId': 13})
    def test_13_get_apps_services_tags_https_www_chase_com_personal_investments_advisor(self):
        # GET https://www.chase.com/apps/services/tags/https/www.chase.com/personal/investments/advisor (endp 13)
        www_chase_com = get_http_client('https://www.chase.com', authenticate)
        resp = www_chase_com.get('/apps/services/tags/https/www.chase.com/personal/investments/advisor')
        resp.assert_status_code(200)

    @clear_session({'spanId': 14})
    def test_14_get_apps_services_tags_https_www_chase_com_personal_investments_advisor_contact_form(self):
        # GET https://www.chase.com/apps/services/tags/https/www.chase.com/personal/investments/advisor-contact-form (endp 14)
        www_chase_com = get_http_client('https://www.chase.com', authenticate)
        resp = www_chase_com.get('/apps/services/tags/https/www.chase.com/personal/investments/advisor-contact-form')
        resp.assert_status_code(200)

    @clear_session({'spanId': 15})
    def test_15_get_apps_services_tags_https_www_chase_com_personal_offers_marketplace(self):
        # GET https://www.chase.com/apps/services/tags/https/www.chase.com/personal/offers/marketplace (endp 15)
        www_chase_com = get_http_client('https://www.chase.com', authenticate)
        resp = www_chase_com.get('/apps/services/tags/https/www.chase.com/personal/offers/marketplace')
        resp.assert_status_code(200)

    @json_dataset('data/dataset_22.json')
    @clear_session({'spanId': 22})
    def test_22_get_content_chase_ux_en_structured_module_param1_index_param2_param3_module_html(self, data_row):
        param, param1, param2 = data_row

        # GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/index/{param2}/{param3}/module.html (endp 22)
        www_chase_com = get_http_client('https://www.chase.com', authenticate)
        resp = www_chase_com.get(f'/content/chase-ux/en/structured/module/{param}/index/{param1}/{param2}/module.html', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @json_dataset('data/dataset_23.json')
    @clear_session({'spanId': 23})
    def test_23_get_content_chase_ux_en_structured_module_param1_prod_public_lucy_advisor_contact_form_mbox_param2_module_html(self, data_row):
        param, param1 = data_row

        # GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/prod-public-lucy-advisor-contact-form-mbox/{param2}/module.html (endp 23)
        www_chase_com = get_http_client('https://www.chase.com', authenticate)
        resp = www_chase_com.get(f'/content/chase-ux/en/structured/module/{param}/prod-public-lucy-advisor-contact-form-mbox/{param1}/module.html', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 20})
    def test_20_get_content_chase_ux_en_structured_module_direct_to_dealer_primary_tool_liquid(self):
        # GET https://www.chase.com/content/chase-ux/en/structured/module/direct-to-dealer/primary-tool-liquid (endp 20)
        www_chase_com = get_http_client('https://www.chase.com', authenticate)
        resp = www_chase_com.get('/content/chase-ux/en/structured/module/direct-to-dealer/primary-tool-liquid')
        resp.assert_status_code(200)
        resp.assert_cssselect('h2#speedbumpDefaultHeading', expected_value="You're now leaving Chase")
        resp.assert_cssselect('html head title', expected_value='primary-tool-liquid')

    @json_dataset('data/dataset_27.json')
    @clear_session({'spanId': 27})
    def test_27_get_content_experience_fragments_microsites_lucy_form_lucylandingpage_master_param_root_html(self, data_row):
        param, = data_row

        # GET https://www.chase.com/content/experience-fragments/microsites/lucy-form/lucylandingpage/master/{param}/root.html (endp 27)
        www_chase_com = get_http_client('https://www.chase.com', authenticate)
        resp = www_chase_com.get(f'/content/experience-fragments/microsites/lucy-form/lucylandingpage/master/{param}/root.html', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        resp.assert_cssselect('div.aem-Grid div.user-details-form.aem-GridColumn div.landingPageForm div.landingPageForm__header div.landingPageForm__header__message p a.chaseanalytics-track-link', expected_value='Sign in here')

    @clear_session({'spanId': 29})
    def test_29_get_personal_investments_advisor_contact_form(self):
        # GET https://www.chase.com/personal/investments/advisor-contact-form (endp 29)
        www_chase_com = get_http_client('https://www.chase.com', authenticate)
        resp = www_chase_com.get('/personal/investments/advisor-contact-form')
        resp.assert_status_code(200)
        resp.assert_cssselect('main#main h1.accessible-text', expected_value='J.P. Morgan Financial Advisor Contact Form | Chase')
        resp.assert_cssselect('html head title', expected_value='J.P. Morgan Financial Advisor Contact Form | Chase')
