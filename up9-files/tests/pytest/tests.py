from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_edge_router(unittest.TestCase):

    @clear_session({'spanId': 5})
    def test_05_get_(self):
        # GET http://edge-router/ (endp 5)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    @clear_session({'spanId': 6})
    def test_06_get_basket_html(self):
        # GET http://edge-router/basket.html (endp 6)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/basket.html')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#basket div.box form h1', expected_value='Shopping cart')

    @clear_session({'spanId': 7})
    def test_07_get_catalogue(self):
        # GET http://edge-router/catalogue (endp 7)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/catalogue')
        resp.assert_ok()
        # resp.assert_status_code(200)

    @clear_session({'spanId': 8})
    def test_08_get_category_html(self):
        # GET http://edge-router/category.html (endp 8)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/category.html')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title', expected_value='Filters ')

    @json_dataset('data/dataset_9.json')
    @clear_session({'spanId': 9})
    def test_09_get_detail_html(self, data_row):
        id_, = data_row

        # GET http://edge-router/detail.html (endp 9)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        qstr = '?' + urlencode([('id', id_)])
        resp = edge_router.get('/detail.html' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#content div.container div div.row.same-height-row div div.box.same-height h3', expected_value='You may also like these products')

    # authentication-related test
    @clear_session({'spanId': 10})
    def test_10_get_login(self):
        # GET http://edge-router/login (endp 10)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', dummy_auth)
        resp = edge_router.get('/login')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('p', expected_value='Cookie is set')


@data_driven_tests
class Tests_front_end(unittest.TestCase):

    @clear_session({'spanId': 11})
    def test_11_get_(self):
        # GET http://front-end/ (endp 11)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    @clear_session({'spanId': 12})
    def test_12_get_basket_html(self):
        # GET http://front-end/basket.html (endp 12)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/basket.html')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#basket div.box form h1', expected_value='Shopping cart')

    @clear_session({'spanId': 13})
    def test_13_get_catalogue(self):
        # GET http://front-end/catalogue (endp 13)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/catalogue')
        resp.assert_ok()
        # resp.assert_status_code(200)

    @clear_session({'spanId': 14})
    def test_14_get_category_html(self):
        # GET http://front-end/category.html (endp 14)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/category.html')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title', expected_value='Filters ')

    @json_dataset('data/dataset_15.json')
    @clear_session({'spanId': 15})
    def test_15_get_detail_html(self, data_row):
        id_, = data_row

        # GET http://front-end/detail.html (endp 15)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        qstr = '?' + urlencode([('id', id_)])
        resp = front_end.get('/detail.html' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#content div.container div div.row.same-height-row div div.box.same-height h3', expected_value='You may also like these products')

    # authentication-related test
    @clear_session({'spanId': 16})
    def test_16_get_login(self):
        # GET http://front-end/login (endp 16)
        front_end = get_http_target('TARGET_FRONT_END', dummy_auth)
        resp = front_end.get('/login')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('p', expected_value='Cookie is set')


@data_driven_tests
class Tests_user(unittest.TestCase):

    @json_dataset('data/dataset_1.json')
    @clear_session({'spanId': 1})
    def test_01_get_customers_id(self, data_row):
        id_, = data_row

        # GET http://user/customers/{id} (endp 1)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get(f'/customers/{id_}')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$.lastName', expected_value='Name')

    @json_dataset('data/dataset_2.json')
    @clear_session({'spanId': 2})
    def test_02_get_customers_id_addresses(self, data_row):
        id_, = data_row

        # GET http://user/customers/{id}/addresses (endp 2)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get(f'/customers/{id_}/addresses')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$._embedded.address.[*].city', expected_value='Glasgow')

    @json_dataset('data/dataset_3.json')
    @clear_session({'spanId': 3})
    def test_03_get_customers_id_cards(self, data_row):
        id_, = data_row

        # GET http://user/customers/{id}/cards (endp 3)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get(f'/customers/{id_}/cards')
        resp.assert_ok()
        # resp.assert_status_code(200)

    # authentication-related test
    @clear_session({'spanId': 4})
    def test_04_get_login(self):
        # GET http://user/login (endp 4)
        user = get_http_target('TARGET_USER', dummy_auth)
        resp = user.get('/login')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$.user.lastName', expected_value='Name')