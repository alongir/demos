from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_bm_adentifi_com(unittest.TestCase):

    @json_dataset('data/dataset_33.json')
    @clear_session({'spanId': 33})
    def test_33_get_pixel_conv_param(self, data_row):
        param, = data_row

        # GET https://bm.adentifi.com/pixel/conv/{param} (endp 33)
        bm_adentifi_com = get_http_client('https://bm.adentifi.com', authenticate)
        resp = bm_adentifi_com.get(f'/pixel/conv/{param}')
        resp.assert_status_code(302)


@data_driven_tests
class Tests_locations_usbank_com(unittest.TestCase):

    @json_dataset('data/dataset_43.json')
    @clear_session({'spanId': 43})
    def test_43_get_param(self, data_row):
        param, = data_row

        # GET https://locations.usbank.com/{param} (endp 43)
        locations_usbank_com = get_http_client('https://locations.usbank.com', authenticate)
        resp = locations_usbank_com.get(f'/{param}')
        resp.assert_status_code(200)
        resp.assert_cssselect('html head title', expected_value='Location Search')

    @clear_session({'spanId': 42})
    def test_42_get_search_html(self):
        # GET https://locations.usbank.com/search.html (endp 42)
        locations_usbank_com = get_http_client('https://locations.usbank.com', authenticate)
        resp = locations_usbank_com.get('/search.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('html head title', expected_value='Location Search')

    @json_dataset('data/dataset_44.json')
    @clear_session({'spanId': 44})
    def test_44_get_search_param(self, data_row):
        param, = data_row

        # GET https://locations.usbank.com/search/{param} (endp 44)
        locations_usbank_com = get_http_client('https://locations.usbank.com', authenticate)
        resp = locations_usbank_com.get(f'/search/{param}', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        resp.assert_cssselect('div#searchRest div.searchResultsLocations div.cq-dd-pages h1.hidden-sm.hidden-xs', expected_value='Search Results')


@data_driven_tests
class Tests_onboarding_usbank_com(unittest.TestCase):

    @json_dataset('data/dataset_38.json')
    @clear_session({'spanId': 38})
    def test_38_post_param_v1_applications_applicationId_patch(self, data_row):
        countriesOfCitizenship, email_, lastName, monthlyHousingPayment, number, op, param, path, productCode = data_row

        # POST https://onboarding.usbank.com/{param}/v1/applications (endp 37)
        onboarding_usbank_com = get_http_client('https://onboarding.usbank.com', authenticate)
        with open('data/payload_for_endp_37.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.products[*].productCode', productCode)
        resp = onboarding_usbank_com.post(f'/{param}/v1/applications', json=json_payload, headers=dict([('x-requested-with', 'X-Requested-With')]))
        resp.assert_status_code(201)
        resp.assert_jsonpath('$.applicants[*].addresses.primary.country', expected_value='US')
        applicationId = jsonpath('$.applicationId', resp)
        securitytoken = get_data_from_header('securitytoken', resp)

        # POST https://onboarding.usbank.com/{param}/v1/applications/{applicationId}/patch (endp 38)
        with open('data/payload_for_endp_38.json', 'r') as json_payload_file:
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

    @json_dataset('data/dataset_39.json')
    @clear_session({'spanId': 39})
    def test_39_get_deposits_depositId_PI_start(self, data_row):
        depositId, = data_row

        # GET https://onboarding.usbank.com/deposits/{depositId}/PI/start (endp 39)
        onboarding_usbank_com = get_http_client('https://onboarding.usbank.com', authenticate)
        resp = onboarding_usbank_com.get(f'/deposits/{depositId}/PI/start')
        resp.assert_status_code(200)
        resp.assert_cssselect('html head title', expected_value='U.S. Bank Checking')

    @json_dataset('data/dataset_40.json')
    @clear_session({'spanId': 40})
    def test_40_post_proxies_v1_validateAddress(self, data_row):
        param, productCode, usAddress, zipCode = data_row

        # POST https://onboarding.usbank.com/{param}/v1/applications (endp 37)
        onboarding_usbank_com = get_http_client('https://onboarding.usbank.com', authenticate)
        with open('data/payload_for_endp_37.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.products[*].productCode', productCode)
        resp = onboarding_usbank_com.post(f'/{param}/v1/applications', json=json_payload, headers=dict([('x-requested-with', 'X-Requested-With')]))
        resp.assert_status_code(201)
        resp.assert_jsonpath('$.applicants[*].addresses.primary.country', expected_value='US')
        securitytoken = get_data_from_header('securitytoken', resp)

        # POST https://onboarding.usbank.com/proxies/v1/validateAddress (endp 40)
        with open('data/payload_for_endp_40.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.customerAddress.usAddress.*', usAddress)
        apply_into_json(json_payload, '$.customerAddress.usAddress.zipCode', zipCode)
        resp = onboarding_usbank_com.post('/proxies/v1/validateAddress', json=json_payload, headers=dict([('securitytoken', securitytoken), ('x-requested-with', 'X-Requested-With')]))
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.address.city', expected_value='NEWARK')


@data_driven_tests
class Tests_onlinebanking_usbank_com(unittest.TestCase):

    @json_dataset('data/dataset_45.json')
    @clear_session({'spanId': 45})
    def test_45_post_param1_xwNT_param2_mpdLQ_param3_param4_IFsXW_param5(self, data_row):
        param, param1, param2, param3, param4, sensor_data = data_row

        # POST https://onlinebanking.usbank.com/{param1}/xwNT/{param2}/mpdLQ/{param3}/{param4}/IFsXW/{param5} (endp 45)
        onlinebanking_usbank_com = get_http_client('https://onlinebanking.usbank.com', authenticate)
        with open('data/payload_for_endp_45.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.sensor_data', sensor_data)
        resp = onlinebanking_usbank_com.post(f'/{param}/xwNT/{param1}/mpdLQ/{param2}/{param3}/IFsXW/{param4}', json=json_payload)
        resp.assert_status_code(201)

    # authentication-related test
    @clear_session({'spanId': 46})
    def test_46_get_OLS_LoginAssist_RetriveId(self):
        # GET https://onlinebanking.usbank.com/OLS/LoginAssist/RetriveId (endp 46)
        onlinebanking_usbank_com = get_http_client('https://onlinebanking.usbank.com', dummy_auth)
        resp = onlinebanking_usbank_com.get('/OLS/LoginAssist/RetriveId')
        resp.assert_status_code(200)
        resp.assert_cssselect('div.mainBodyWidth div.body-content.bodyDiv div div.la__touchnav-container a', expected_value='Help')
        resp.assert_cssselect('html head title', expected_value='Login Assistance')

    @clear_session({'spanId': 47})
    def test_47_get_OLS_Public_Enrollment_Index(self):
        # GET https://onlinebanking.usbank.com/OLS/Public/Enrollment/Index (endp 47)
        onlinebanking_usbank_com = get_http_client('https://onlinebanking.usbank.com', authenticate)
        qstr = '?' + urlencode([('isPartnerOLB', 'true')])
        resp = onlinebanking_usbank_com.get('/OLS/Public/Enrollment/Index' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('html head title', expected_value='Enrollment')


@data_driven_tests
class Tests_sales_usbank_com(unittest.TestCase):

    @json_dataset('data/dataset_41.json')
    @clear_session({'spanId': 41})
    def test_41_get_product_configuration_v1_products_usbank_param_PI(self, data_row):
        param, = data_row

        # GET https://sales.usbank.com/product-configuration/v1/products/usbank/{param}/PI (endp 41)
        sales_usbank_com = get_http_client('https://sales.usbank.com', authenticate)
        resp = sales_usbank_com.get(f'/product-configuration/v1/products/usbank/{param}/PI', headers=dict([('x-requested-with', 'X-Requested-With')]))
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.tenant', expected_value='usbank')


@data_driven_tests
class Tests_siteintercept_qualtrics_com(unittest.TestCase):

    @json_dataset('data/dataset_20.json')
    @clear_session({'spanId': 20})
    def test_20_post_WRSiteInterceptEngine_(self, data_row):
        BrandDC, Module, Q_CLIENTVERSION, Q_CLIENTVERSION1, Q_CLIENTVERSION2, Q_InterceptID, Q_ORIGIN = data_row

        # GET https://www.usbank.com/index.html (endp 4)
        www_usbank_com = get_http_client('https://www.usbank.com', authenticate)
        resp = www_usbank_com.get('/index.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('html head title', expected_value='Consumer banking | Personal banking | U.S. Bank')
        Q_LOC = cssselect('html head link[href] @href', resp)

        # GET https://siteintercept.qualtrics.com/WRSiteInterceptEngine/Asset.php (endp 21)
        siteintercept_qualtrics_com = get_http_client('https://siteintercept.qualtrics.com', authenticate)
        qstr = '?' + urlencode([('Module', Module), ('Q_CLIENTTYPE', 'web'), ('Q_CLIENTVERSION', Q_CLIENTVERSION), ('Q_InterceptID', Q_InterceptID), ('Q_ORIGIN', Q_ORIGIN), ('Version', str(random.randint(1, 26)))])
        resp = siteintercept_qualtrics_com.get('/WRSiteInterceptEngine/Asset.php' + qstr)
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.CreativeDefinition.Options.Message.Headline.Text', expected_value='Will you take our survey?')
        Q_ZoneID = jsonpath('$.CreativeDefinition.ZoneID', resp)

        # POST https://siteintercept.qualtrics.com/WRSiteInterceptEngine/Targeting.php (endp 22)
        qstr = '?' + urlencode([('Q_CLIENTTYPE', 'web'), ('Q_CLIENTVERSION', Q_CLIENTVERSION1), ('Q_ZoneID', Q_ZoneID)])
        resp = siteintercept_qualtrics_com.post('/WRSiteInterceptEngine/Targeting.php' + qstr, data=[('Q_LOC', Q_LOC)])
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.ClientSideIntercepts[*].LogicTree.Left.Left.Type', expected_value='LogicNode')
        Q_ASID = jsonpath('$.ClientSideIntercepts[*].ActionSets[*].ActionSetID', resp)
        Q_CID = url_part('?Q_CID', jsonpath('$.ClientSideIntercepts[*].ActionSets[*].Target.*', resp))
        Q_SIID = url_part('?Q_SIID', jsonpath('$.ClientSideIntercepts[*].ActionSets[*].Target.*', resp))
        SurveyID = url_part('/3', jsonpath('$.ClientSideIntercepts[*].ActionSets[*].Target.OriginalURL', resp))

        # POST https://siteintercept.qualtrics.com/WRSiteInterceptEngine/ (endp 20)
        qstr = '?' + urlencode([('Q_ASID', Q_ASID), ('Q_CID', Q_CID), ('Q_CLIENTTYPE', 'web'), ('Q_CLIENTVERSION', Q_CLIENTVERSION2), ('Q_Impress', '1'), ('Q_SIID', Q_SIID), ('r', str(int(time.time() * 1000)))])
        resp = siteintercept_qualtrics_com.post('/WRSiteInterceptEngine/' + qstr, data=[('BrandDC', BrandDC), ('BrandID', 'usbank'), ('SurveyID', SurveyID)])
        resp.assert_status_code(200)


@data_driven_tests
class Tests_usbank_app_quantummetric_com(unittest.TestCase):

    @json_dataset('data/dataset_23.json')
    @clear_session({'spanId': 23})
    def test_23_post_(self, data_row):
        H, PRODUCT_CODE, U, s = data_row

        # GET https://apply.usbank.com/apply/apply.html (endp 36)
        apply_usbank_com = get_http_client('https://apply.usbank.com', authenticate)
        qstr = '?' + urlencode([('PRODUCT_CODE', PRODUCT_CODE), ('SUB_PRODUCT_CODE', 'PI')])
        resp = apply_usbank_com.get('/apply/apply.html' + qstr)
        resp.assert_status_code(302)
        u = get_data_from_header('location', resp)

        # POST https://usbank-app.quantummetric.com/ (endp 23)
        usbank_app_quantummetric_com = get_http_client('https://usbank-app.quantummetric.com', authenticate)
        qstr = '?' + urlencode([('H', H), ('N', str(random.randint(0, 1434))), ('P', str(random.randint(0, 15))), ('Q', '2'), ('S', str(random.randint(0, 102475))), ('T', 'B'), ('U', U), ('s', s), ('t', str(int(time.time() * 1000))), ('u', u), ('v', str(int(time.time() * 1000))), ('z', '1')])
        with open('data/payload_for_endp_23.txt', 'r') as payload_file:
            payload = payload_file.read()
        resp = usbank_app_quantummetric_com.post('/' + qstr, data=payload, headers=dict([('content-type', 'text/plain')]))
        resp.assert_status_code(200)

    @json_dataset('data/dataset_24.json')
    @clear_session({'spanId': 24})
    def test_24_get_(self, data_row):
        H, Module, Q, Q_CLIENTVERSION, Q_CLIENTVERSION1, Q_InterceptID, Q_ORIGIN, s = data_row

        # GET https://www.usbank.com/index.html (endp 4)
        www_usbank_com = get_http_client('https://www.usbank.com', authenticate)
        resp = www_usbank_com.get('/index.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('html head title', expected_value='Consumer banking | Personal banking | U.S. Bank')
        Q_LOC = cssselect('html head link[href] @href', resp)

        # GET https://siteintercept.qualtrics.com/WRSiteInterceptEngine/Asset.php (endp 21)
        siteintercept_qualtrics_com = get_http_client('https://siteintercept.qualtrics.com', authenticate)
        qstr = '?' + urlencode([('Module', Module), ('Q_CLIENTTYPE', 'web'), ('Q_CLIENTVERSION', Q_CLIENTVERSION), ('Q_InterceptID', Q_InterceptID), ('Q_ORIGIN', Q_ORIGIN), ('Version', str(random.randint(1, 26)))])
        resp = siteintercept_qualtrics_com.get('/WRSiteInterceptEngine/Asset.php' + qstr)
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.CreativeDefinition.Options.Message.Headline.Text', expected_value='Will you take our survey?')
        Q_ZoneID = jsonpath('$.CreativeDefinition.ZoneID', resp)

        # POST https://siteintercept.qualtrics.com/WRSiteInterceptEngine/Targeting.php (endp 22)
        qstr = '?' + urlencode([('Q_CLIENTTYPE', 'web'), ('Q_CLIENTVERSION', Q_CLIENTVERSION1), ('Q_ZoneID', Q_ZoneID)])
        resp = siteintercept_qualtrics_com.post('/WRSiteInterceptEngine/Targeting.php' + qstr, data=[('Q_LOC', Q_LOC)])
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.ClientSideIntercepts[*].LogicTree.Left.Left.Type', expected_value='LogicNode')

        # GET https://usbank-app.quantummetric.com/ (endp 24)
        usbank_app_quantummetric_com = get_http_client('https://usbank-app.quantummetric.com', authenticate)
        qstr = '?' + urlencode([('H', H), ('Q', Q), ('s', s)])
        resp = usbank_app_quantummetric_com.get('/' + qstr)
        resp.assert_status_code(200)


@data_driven_tests
class Tests_usbank_sync_quantummetric_com(unittest.TestCase):

    @json_dataset('data/dataset_25.json')
    @clear_session({'spanId': 25})
    def test_25_post_(self, data_row):
        Module, Q_CLIENTVERSION, Q_CLIENTVERSION1, Q_InterceptID, Q_ORIGIN, X, s = data_row

        # GET https://www.usbank.com/index.html (endp 4)
        www_usbank_com = get_http_client('https://www.usbank.com', authenticate)
        resp = www_usbank_com.get('/index.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('html head title', expected_value='Consumer banking | Personal banking | U.S. Bank')
        Q_LOC = cssselect('html head link[href] @href', resp)
        u = cssselect('html head link[href] @href', resp)

        # GET https://siteintercept.qualtrics.com/WRSiteInterceptEngine/Asset.php (endp 21)
        siteintercept_qualtrics_com = get_http_client('https://siteintercept.qualtrics.com', authenticate)
        qstr = '?' + urlencode([('Module', Module), ('Q_CLIENTTYPE', 'web'), ('Q_CLIENTVERSION', Q_CLIENTVERSION), ('Q_InterceptID', Q_InterceptID), ('Q_ORIGIN', Q_ORIGIN), ('Version', str(random.randint(1, 26)))])
        resp = siteintercept_qualtrics_com.get('/WRSiteInterceptEngine/Asset.php' + qstr)
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.CreativeDefinition.Options.Message.Headline.Text', expected_value='Will you take our survey?')
        Q_ZoneID = jsonpath('$.CreativeDefinition.ZoneID', resp)

        # POST https://siteintercept.qualtrics.com/WRSiteInterceptEngine/Targeting.php (endp 22)
        qstr = '?' + urlencode([('Q_CLIENTTYPE', 'web'), ('Q_CLIENTVERSION', Q_CLIENTVERSION1), ('Q_ZoneID', Q_ZoneID)])
        resp = siteintercept_qualtrics_com.post('/WRSiteInterceptEngine/Targeting.php' + qstr, data=[('Q_LOC', Q_LOC)])
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.ClientSideIntercepts[*].LogicTree.Left.Left.Type', expected_value='LogicNode')

        # POST https://usbank-sync.quantummetric.com/ (endp 25)
        usbank_sync_quantummetric_com = get_http_client('https://usbank-sync.quantummetric.com', authenticate)
        qstr = '?' + urlencode([('Q', '1'), ('T', 'B'), ('X', X), ('Y', '1'), ('s', s), ('t', str(int(time.time() * 1000))), ('u', u), ('v', str(int(time.time() * 1000))), ('z', '1')])
        with open('data/payload_for_endp_25.txt', 'r') as payload_file:
            payload = payload_file.read()
        resp = usbank_sync_quantummetric_com.post('/' + qstr, data=payload, headers=dict([('content-type', 'text/plain')]))
        resp.assert_status_code(200)


@data_driven_tests
class Tests_www_usbank_com(unittest.TestCase):

    @clear_session({'spanId': 1})
    def test_01_get_about_us_bank_customer_service_html(self):
        # GET https://www.usbank.com/about-us-bank/customer-service.html (endp 1)
        www_usbank_com = get_http_client('https://www.usbank.com', authenticate)
        resp = www_usbank_com.get('/about-us-bank/customer-service.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('html head title', expected_value='Customer service | U.S. Bank')

    @clear_session({'spanId': 2})
    def test_02_get_bank_accounts_checking_accounts_html(self):
        # GET https://www.usbank.com/bank-accounts/checking-accounts.html (endp 2)
        www_usbank_com = get_http_client('https://www.usbank.com', authenticate)
        resp = www_usbank_com.get('/bank-accounts/checking-accounts.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('dialog.dialog.shield-zipcodes[name="#saveXZip"] div.content div.heading.large h1', expected_value='Zip Code')
        resp.assert_cssselect('html head title', expected_value='Checking accounts | Open a Personal Checking Account | U.S. Bank')

    @clear_session({'spanId': 3})
    def test_03_get_bank_accounts_checking_accounts_gold_checking_account_html(self):
        # GET https://www.usbank.com/bank-accounts/checking-accounts/gold-checking-account.html (endp 3)
        www_usbank_com = get_http_client('https://www.usbank.com', authenticate)
        resp = www_usbank_com.get('/bank-accounts/checking-accounts/gold-checking-account.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('section.pubIns.productDetailsPage div div.bodyContent.container-fluid div.row div.bannerResponsiveGrid div div.aem-Grid div.banner.parbase.aem-GridColumn div.USBDesignSystem--Shield.USBHero div div.USBHero__Container.clearfix div.clearfix div.text div div.textContainer h1', expected_value='U.S. BANK GOLD CHECKING PACKAGE')
        resp.assert_cssselect('html head title', expected_value='Gold Checking account | Personal Checking account | U.S. Bank')

    @json_dataset('data/dataset_5.json')
    @clear_session({'spanId': 5})
    def test_05_post_plpXRb_YlO_param1_param2_param3_param4_aEs_aeId(self, data_row):
        aeId, param, param1, param2, param3, sensor_data = data_row

        # POST https://www.usbank.com/plpXRb/YlO/{param1}/{param2}/{param3}/{param4}/aEs/{aeId} (endp 5)
        www_usbank_com = get_http_client('https://www.usbank.com', authenticate)
        with open('data/payload_for_endp_5.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.sensor_data', sensor_data)
        resp = www_usbank_com.post(f'/plpXRb/YlO/{param}/{param1}/{param2}/{param3}/aEs/{aeId}', json=json_payload)
        resp.assert_status_code(201)

    @json_dataset('data/dataset_6.json')
    @clear_session({'spanId': 6})
    def test_06_post_svt_usbank_shield_fetchDisclosureContent(self, data_row):
        businessLines, disclosureTitles = data_row

        # POST https://www.usbank.com/svt/usbank/shield/fetchDisclosureContent (endp 6)
        www_usbank_com = get_http_client('https://www.usbank.com', authenticate)
        resp = www_usbank_com.post('/svt/usbank/shield/fetchDisclosureContent', data=[('businessLines', businessLines), ('disclosureTitles', disclosureTitles)], headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        resp.assert_jsonpath('$[*].status', expected_value='success')
