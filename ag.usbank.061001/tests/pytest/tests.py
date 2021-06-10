from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_bm_adentifi_com(unittest.TestCase):

    @json_dataset('data/dataset_30.json')
    @clear_session({'spanId': 30})
    def test_30_get_pixel_conv_param(self, data_row):
        param, = data_row

        # GET https://bm.adentifi.com/pixel/conv/{param} (endp 30)
        bm_adentifi_com = get_http_client('https://bm.adentifi.com', authenticate)
        resp = bm_adentifi_com.get(f'/pixel/conv/{param}')
        resp.assert_status_code(302)


@data_driven_tests
class Tests_locations_usbank_com(unittest.TestCase):

    @json_dataset('data/dataset_40.json')
    @clear_session({'spanId': 40})
    def test_40_get_param(self, data_row):
        param, = data_row

        # GET https://locations.usbank.com/{param} (endp 40)
        locations_usbank_com = get_http_client('https://locations.usbank.com', authenticate)
        resp = locations_usbank_com.get(f'/{param}')
        resp.assert_status_code(200)
        resp.assert_cssselect('html head title', expected_value='Location Search')

    @clear_session({'spanId': 39})
    def test_39_get_search_html(self):
        # GET https://locations.usbank.com/search.html (endp 39)
        locations_usbank_com = get_http_client('https://locations.usbank.com', authenticate)
        resp = locations_usbank_com.get('/search.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('html head title', expected_value='Location Search')

    @json_dataset('data/dataset_41.json')
    @clear_session({'spanId': 41})
    def test_41_get_search_param(self, data_row):
        param, = data_row

        # GET https://locations.usbank.com/search/{param} (endp 41)
        locations_usbank_com = get_http_client('https://locations.usbank.com', authenticate)
        resp = locations_usbank_com.get(f'/search/{param}', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        resp.assert_cssselect('div#searchRest div.searchResultsLocations div.cq-dd-pages h1.hidden-sm.hidden-xs', expected_value='Search Results')


@data_driven_tests
class Tests_onboarding_usbank_com(unittest.TestCase):

    @json_dataset('data/dataset_35.json')
    @clear_session({'spanId': 35})
    def test_35_post_param_v1_applications_applicationId_patch(self, data_row):
        countriesOfCitizenship, email_, lastName, monthlyHousingPayment, number, op, param, path, productCode = data_row

        # POST https://onboarding.usbank.com/{param}/v1/applications (endp 34)
        onboarding_usbank_com = get_http_client('https://onboarding.usbank.com', authenticate)
        with open('data/payload_for_endp_34.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.products[*].productCode', productCode)
        resp = onboarding_usbank_com.post(f'/{param}/v1/applications', json=json_payload, headers=dict([('x-requested-with', 'X-Requested-With')]))
        resp.assert_status_code(201)
        resp.assert_jsonpath('$.applicants[*].addresses.primary.country', expected_value='US')
        applicationId = jsonpath('$.applicationId', resp)
        securitytoken = get_data_from_header('securitytoken', resp)

        # POST https://onboarding.usbank.com/{param}/v1/applications/{applicationId}/patch (endp 35)
        with open('data/payload_for_endp_35.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$[*].op', op)
        apply_into_json(json_payload, '$[*].path', path)
        apply_into_json(json_payload, '$[*].value.countriesOfCitizenship[*]', countriesOfCitizenship)
        apply_into_json(json_payload, '$[*].value.lastName', lastName)
        apply_into_json(json_payload, '$[*].value.monthlyHousingPayment', monthlyHousingPayment)
        apply_into_json(json_payload, '$[*].value.number', number)
        apply_into_json(json_payload, '$[*].value.personal.email', email_)
        resp = onboarding_usbank_com.post(f'/{param}/v1/applications/{applicationId}/patch', json=json_payload, headers=dict([('securitytoken', securitytoken), ('x-requested-with', 'X-Requested-With')]))
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.applicants[*].addresses.primary.country', expected_value='US')

    @json_dataset('data/dataset_36.json')
    @clear_session({'spanId': 36})
    def test_36_get_deposits_depositId_PI_start(self, data_row):
        depositId, = data_row

        # GET https://onboarding.usbank.com/deposits/{depositId}/PI/start (endp 36)
        onboarding_usbank_com = get_http_client('https://onboarding.usbank.com', authenticate)
        resp = onboarding_usbank_com.get(f'/deposits/{depositId}/PI/start')
        resp.assert_status_code(200)
        resp.assert_cssselect('html head title', expected_value='U.S. Bank Checking')

    @json_dataset('data/dataset_37.json')
    @clear_session({'spanId': 37})
    def test_37_post_proxies_v1_validateAddress(self, data_row):
        param, productCode, usAddress, zipCode = data_row

        # POST https://onboarding.usbank.com/{param}/v1/applications (endp 34)
        onboarding_usbank_com = get_http_client('https://onboarding.usbank.com', authenticate)
        with open('data/payload_for_endp_34.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.products[*].productCode', productCode)
        resp = onboarding_usbank_com.post(f'/{param}/v1/applications', json=json_payload, headers=dict([('x-requested-with', 'X-Requested-With')]))
        resp.assert_status_code(201)
        resp.assert_jsonpath('$.applicants[*].addresses.primary.country', expected_value='US')
        securitytoken = get_data_from_header('securitytoken', resp)

        # POST https://onboarding.usbank.com/proxies/v1/validateAddress (endp 37)
        with open('data/payload_for_endp_37.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.customerAddress.usAddress.*', usAddress)
        apply_into_json(json_payload, '$.customerAddress.usAddress.zipCode', zipCode)
        resp = onboarding_usbank_com.post('/proxies/v1/validateAddress', json=json_payload, headers=dict([('securitytoken', securitytoken), ('x-requested-with', 'X-Requested-With')]))
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.address.city', expected_value='NEWARK')


@data_driven_tests
class Tests_sales_usbank_com(unittest.TestCase):

    @json_dataset('data/dataset_38.json')
    @clear_session({'spanId': 38})
    def test_38_get_product_configuration_v1_products_usbank_param_PI(self, data_row):
        param, = data_row

        # GET https://sales.usbank.com/product-configuration/v1/products/usbank/{param}/PI (endp 38)
        sales_usbank_com = get_http_client('https://sales.usbank.com', authenticate)
        resp = sales_usbank_com.get(f'/product-configuration/v1/products/usbank/{param}/PI', headers=dict([('x-requested-with', 'X-Requested-With')]))
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.tenant', expected_value='usbank')


@data_driven_tests
class Tests_siteintercept_qualtrics_com(unittest.TestCase):

    @json_dataset('data/dataset_18.json')
    @clear_session({'spanId': 18})
    def test_18_post_WRSiteInterceptEngine_(self, data_row):
        BrandDC, Module, Q_CLIENTVERSION, Q_CLIENTVERSION1, Q_CLIENTVERSION2, Q_InterceptID, Q_ORIGIN = data_row

        # GET https://www.usbank.com/bank-accounts/checking-accounts.html (endp 1)
        www_usbank_com = get_http_client('https://www.usbank.com', authenticate)
        resp = www_usbank_com.get('/bank-accounts/checking-accounts.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('dialog.dialog.shield-zipcodes[name="#saveXZip"] div.content div.heading.large h1', expected_value='Zip Code')
        resp.assert_cssselect('html head title', expected_value='Checking accounts | Open a Personal Checking Account | U.S. Bank')
        Q_LOC = cssselect('html head meta[content] @content', resp)

        # GET https://siteintercept.qualtrics.com/WRSiteInterceptEngine/Asset.php (endp 19)
        siteintercept_qualtrics_com = get_http_client('https://siteintercept.qualtrics.com', authenticate)
        qstr = '?' + urlencode([('Module', Module), ('Q_CLIENTTYPE', 'web'), ('Q_CLIENTVERSION', Q_CLIENTVERSION), ('Q_InterceptID', Q_InterceptID), ('Q_ORIGIN', Q_ORIGIN), ('Version', str(random.randint(1, 26)))])
        resp = siteintercept_qualtrics_com.get('/WRSiteInterceptEngine/Asset.php' + qstr)
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.CreativeDefinition.Options.Message.Headline.Text', expected_value='Will you take our survey?')
        Q_ZoneID = jsonpath('$.CreativeDefinition.ZoneID', resp)

        # POST https://siteintercept.qualtrics.com/WRSiteInterceptEngine/Targeting.php (endp 20)
        qstr = '?' + urlencode([('Q_CLIENTTYPE', 'web'), ('Q_CLIENTVERSION', Q_CLIENTVERSION1), ('Q_ZoneID', Q_ZoneID)])
        resp = siteintercept_qualtrics_com.post('/WRSiteInterceptEngine/Targeting.php' + qstr, data=[('Q_LOC', Q_LOC)])
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.ClientSideIntercepts[*].LogicTree.Left.Left.Type', expected_value='LogicNode')
        Q_ASID = jsonpath('$.ClientSideIntercepts[*].ActionSets[*].ActionSetID', resp)
        Q_CID = url_part('?Q_CID', jsonpath('$.ClientSideIntercepts[*].ActionSets[*].Target.*', resp))
        Q_SIID = url_part('?Q_SIID', jsonpath('$.ClientSideIntercepts[*].ActionSets[*].Target.*', resp))
        SurveyID = url_part('/3', jsonpath('$.ClientSideIntercepts[*].ActionSets[*].Target.OriginalURL', resp))

        # POST https://siteintercept.qualtrics.com/WRSiteInterceptEngine/ (endp 18)
        qstr = '?' + urlencode([('Q_ASID', Q_ASID), ('Q_CID', Q_CID), ('Q_CLIENTTYPE', 'web'), ('Q_CLIENTVERSION', Q_CLIENTVERSION2), ('Q_Impress', '1'), ('Q_SIID', Q_SIID), ('r', str(int(time.time() * 1000)))])
        resp = siteintercept_qualtrics_com.post('/WRSiteInterceptEngine/' + qstr, data=[('BrandDC', BrandDC), ('BrandID', 'usbank'), ('SurveyID', SurveyID)])
        resp.assert_status_code(200)


@data_driven_tests
class Tests_usbank_app_quantummetric_com(unittest.TestCase):

    @json_dataset('data/dataset_21.json')
    @clear_session({'spanId': 21})
    def test_21_post_(self, data_row):
        H, PRODUCT_CODE, U, s = data_row

        # GET https://apply.usbank.com/apply/apply.html (endp 33)
        apply_usbank_com = get_http_client('https://apply.usbank.com', authenticate)
        qstr = '?' + urlencode([('PRODUCT_CODE', PRODUCT_CODE), ('SUB_PRODUCT_CODE', 'PI')])
        resp = apply_usbank_com.get('/apply/apply.html' + qstr)
        resp.assert_status_code(302)
        u = get_data_from_header('location', resp)

        # POST https://usbank-app.quantummetric.com/ (endp 21)
        usbank_app_quantummetric_com = get_http_client('https://usbank-app.quantummetric.com', authenticate)
        qstr = '?' + urlencode([('H', H), ('N', str(random.randint(0, 1434))), ('P', str(random.randint(0, 15))), ('Q', '2'), ('S', str(random.randint(0, 102475))), ('T', 'B'), ('U', U), ('s', s), ('t', str(int(time.time() * 1000))), ('u', u), ('v', str(int(time.time() * 1000))), ('z', '1')])
        with open('data/payload_for_endp_21.txt', 'r') as payload_file:
            payload = payload_file.read()
        resp = usbank_app_quantummetric_com.post('/' + qstr, data=payload, headers=dict([('content-type', 'text/plain')]))
        resp.assert_status_code(200)

    @json_dataset('data/dataset_22.json')
    @clear_session({'spanId': 22})
    def test_22_get_(self, data_row):
        H, Q, s = data_row

        # GET https://usbank-app.quantummetric.com/ (endp 22)
        usbank_app_quantummetric_com = get_http_client('https://usbank-app.quantummetric.com', authenticate)
        qstr = '?' + urlencode([('H', H), ('Q', Q), ('s', s)])
        resp = usbank_app_quantummetric_com.get('/' + qstr)
        resp.assert_status_code(200)


@data_driven_tests
class Tests_usbank_sync_quantummetric_com(unittest.TestCase):

    @json_dataset('data/dataset_23.json')
    @clear_session({'spanId': 23})
    def test_23_post_(self, data_row):
        PRODUCT_CODE, X, s = data_row

        # GET https://apply.usbank.com/apply/apply.html (endp 33)
        apply_usbank_com = get_http_client('https://apply.usbank.com', authenticate)
        qstr = '?' + urlencode([('PRODUCT_CODE', PRODUCT_CODE), ('SUB_PRODUCT_CODE', 'PI')])
        resp = apply_usbank_com.get('/apply/apply.html' + qstr)
        resp.assert_status_code(302)
        u = get_data_from_header('location', resp)

        # POST https://usbank-sync.quantummetric.com/ (endp 23)
        usbank_sync_quantummetric_com = get_http_client('https://usbank-sync.quantummetric.com', authenticate)
        qstr = '?' + urlencode([('Q', '1'), ('T', 'B'), ('X', X), ('Y', '1'), ('s', s), ('t', str(int(time.time() * 1000))), ('u', u), ('v', str(int(time.time() * 1000))), ('z', '1')])
        with open('data/payload_for_endp_23.txt', 'r') as payload_file:
            payload = payload_file.read()
        resp = usbank_sync_quantummetric_com.post('/' + qstr, data=payload, headers=dict([('content-type', 'text/plain')]))
        resp.assert_status_code(200)


@data_driven_tests
class Tests_www_usbank_com(unittest.TestCase):

    @clear_session({'spanId': 2})
    def test_02_get_bank_accounts_checking_accounts_gold_checking_account_html(self):
        # GET https://www.usbank.com/bank-accounts/checking-accounts/gold-checking-account.html (endp 2)
        www_usbank_com = get_http_client('https://www.usbank.com', authenticate)
        resp = www_usbank_com.get('/bank-accounts/checking-accounts/gold-checking-account.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('section.pubIns.productDetailsPage div div.bodyContent.container-fluid div.row div.bannerResponsiveGrid div div.aem-Grid div.banner.parbase.aem-GridColumn div.USBDesignSystem--Shield.USBHero div div.USBHero__Container.clearfix div.clearfix div.text div div.textContainer h1', expected_value='U.S. BANK GOLD CHECKING PACKAGE')
        resp.assert_cssselect('html head title', expected_value='Gold Checking account | Personal Checking account | U.S. Bank')

    @clear_session({'spanId': 3})
    def test_03_get_index_html(self):
        # GET https://www.usbank.com/index.html (endp 3)
        www_usbank_com = get_http_client('https://www.usbank.com', authenticate)
        resp = www_usbank_com.get('/index.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('html head title', expected_value='Consumer banking | Personal banking | U.S. Bank')

    @json_dataset('data/dataset_4.json')
    @clear_session({'spanId': 4})
    def test_04_post_plpXRb_YlO_param1_param2_param3_param4_aEs_aeId(self, data_row):
        aeId, param, param1, param2, param3, sensor_data = data_row

        # POST https://www.usbank.com/plpXRb/YlO/{param1}/{param2}/{param3}/{param4}/aEs/{aeId} (endp 4)
        www_usbank_com = get_http_client('https://www.usbank.com', authenticate)
        with open('data/payload_for_endp_4.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.sensor_data', sensor_data)
        resp = www_usbank_com.post(f'/plpXRb/YlO/{param}/{param1}/{param2}/{param3}/aEs/{aeId}', json=json_payload)
        resp.assert_status_code(201)

    @json_dataset('data/dataset_5.json')
    @clear_session({'spanId': 5})
    def test_05_post_svt_usbank_shield_fetchDisclosureContent(self, data_row):
        businessLines, disclosureTitles = data_row

        # POST https://www.usbank.com/svt/usbank/shield/fetchDisclosureContent (endp 5)
        www_usbank_com = get_http_client('https://www.usbank.com', authenticate)
        resp = www_usbank_com.post('/svt/usbank/shield/fetchDisclosureContent', data=[('businessLines', businessLines), ('disclosureTitles', disclosureTitles)], headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        resp.assert_jsonpath('$[*].status', expected_value='success')
