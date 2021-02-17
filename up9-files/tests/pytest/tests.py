from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_api(unittest.TestCase):

    @clear_session({'spanId': 7})
    def test_7_get_(self):
        # GET http://api/ (endp 7)
        api = get_http_target('TARGET_API', authenticate)
        resp = api.get('/')
        resp.assert_ok()
        # resp.assert_jsonpath('$.upstream_calls.[*].type', expected_value='HTTP1')


@data_driven_tests
class Tests_cache(unittest.TestCase):

    @clear_session({'spanId': 5})
    def test_5_get_(self):
        # GET http://cache/ (endp 5)
        cache = get_http_target('TARGET_CACHE', authenticate)
        resp = cache.get('/')
        resp.assert_ok()
        # resp.assert_jsonpath('$.upstream_calls.[*].body', expected_value='Payments response')


@data_driven_tests
class Tests_currency(unittest.TestCase):

    @clear_session({'spanId': 1})
    def test_1_get_(self):
        # GET http://currency/ (endp 1)
        currency = get_http_target('TARGET_CURRENCY', authenticate)
        resp = currency.get('/')
        resp.assert_ok()
        # resp.assert_jsonpath('$.body', expected_value='Currency response')


@data_driven_tests
class Tests_payments(unittest.TestCase):

    @clear_session({'spanId': 6})
    def test_6_get_(self):
        # GET http://payments/ (endp 6)
        payments = get_http_target('TARGET_PAYMENTS', authenticate)
        resp = payments.get('/')
        resp.assert_ok()
        # resp.assert_jsonpath('$.upstream_calls.[*].body', expected_value='Currency response')


@data_driven_tests
class Tests_web(unittest.TestCase):

    @clear_session({'spanId': 2})
    def test_2_get_(self):
        # GET http://web/ (endp 2)
        web = get_http_target('TARGET_WEB', authenticate)
        resp = web.get('/')
        resp.assert_ok()
        # resp.assert_jsonpath('$.upstream_calls.[*].name', expected_value='api')

    @clear_session({'spanId': 3})
    def test_3_get_ui_(self):
        # GET http://web/ui/ (endp 3)
        web = get_http_target('TARGET_WEB', authenticate)
        resp = web.get('/ui/')
        resp.assert_ok()
        # resp.assert_status_code(200)

    @clear_session({'spanId': 4})
    def test_4_get_ui_manifest_json(self):
        # GET http://web/ui/manifest.json (endp 4)
        web = get_http_target('TARGET_WEB', authenticate)
        resp = web.get('/ui/manifest.json')
        resp.assert_ok()
        # resp.assert_jsonpath('$.name', expected_value='Create React App Sample')
