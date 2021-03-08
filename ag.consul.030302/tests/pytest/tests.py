from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_carts(unittest.TestCase):

    @clear_session({'spanId': 13})
    def test_13_get_carts_id_items(self):
        # GET http://carts/carts/{id}/items (endp 13)
        id_ = '57a98d98e4b00679b4a830b2'
        carts = get_http_target('TARGET_CARTS', authenticate)
        resp = carts.get(f'/carts/{id_}/items')
        resp.assert_status_code(200)

    @clear_session({'spanId': 12})
    def test_12_get_carts_id_merge(self):
        # GET http://carts/carts/{id}/merge (endp 12)
        id_ = '57a98d98e4b00679b4a830b2'
        sessionId = '7w4TGI0pnIxn5TEFoHX6O2spPdXa3dut'
        carts = get_http_target('TARGET_CARTS', authenticate)
        qstr = '?' + urlencode([('sessionId', sessionId)])
        resp = carts.get(f'/carts/{id_}/merge' + qstr)
        resp.assert_status_code(202)


@data_driven_tests
class Tests_catalogue(unittest.TestCase):

    @clear_session({'spanId': 10})
    def test_10_get_catalogue(self):
        # GET http://catalogue/catalogue (endp 10)
        size = '5'
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        qstr = '?' + urlencode([('page', '1'), ('size', size), ('tags', '')])
        resp = catalogue.get('/catalogue' + qstr)
        resp.assert_status_code(200)

    @clear_session({'spanId': 9})
    def test_09_get_catalogue_size(self):
        # GET http://catalogue/catalogue/size (endp 9)
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        qstr = '?' + urlencode([('tags', '')])
        resp = catalogue.get('/catalogue/size' + qstr)
        resp.assert_status_code(200)

    @clear_session({'spanId': 11})
    def test_11_get_tags(self):
        # GET http://catalogue/tags (endp 11)
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        resp = catalogue.get('/tags')
        resp.assert_status_code(200)


@data_driven_tests
class Tests_front_end(unittest.TestCase):

    @clear_session({'spanId': 1})
    def test_01_get_(self):
        # GET http://front-end/ (endp 1)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    @clear_session({'spanId': 26})
    def test_26_get_param(self):
        # GET http://front-end/{param} (endp 26)
        param = 'navbar.html'
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get(f'/{param}', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 2})
    def test_02_get_cart(self):
        # GET http://front-end/cart (endp 2)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/cart', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 4})
    def test_04_get_catalogue(self):
        # GET http://front-end/catalogue (endp 4)
        size = '5'
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        qstr = '?' + urlencode([('page', '1'), ('size', size), ('tags', '')])
        resp = front_end.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 3})
    def test_03_get_catalogue_size(self):
        # GET http://front-end/catalogue/size (endp 3)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        qstr = '?' + urlencode([('tags', '')])
        resp = front_end.get('/catalogue/size' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 34})
    def test_34_get_category_html(self):
        # GET http://front-end/category.html (endp 34)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/category.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title', expected_value='Filters ')

    @clear_session({'spanId': 5})
    def test_05_get_customers_customerId(self):
        # GET http://front-end/customers/{customerId} (endp 5)
        customerId = '7w4TGI0pnIxn5TEFoHX6O2spPdXa3dut'
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get(f'/customers/{customerId}', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.lastName', expected_value='Name')

    @clear_session({'spanId': 30})
    def test_30_get_customers_customerId(self):
        # GET http://front-end/customers/{customerId} (endp 30)
        customerId = '7w4TGI0pnIxn5TEFoHX6O2spPdXa3dut'
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get(f'/customers/{customerId}', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 23})
    def test_23_get_footer_html(self):
        # GET http://front-end/footer.html (endp 23)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/footer.html', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        resp.assert_cssselect('div#copyright div.container div p.pull-left a', expected_value='Weaveworks')

    @clear_session({'spanId': 6})
    def test_06_get_index_html(self):
        # GET http://front-end/index.html (endp 6)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/index.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    # authentication-related test
    @clear_session({'spanId': 25})
    def test_25_get_login(self):
        # GET http://front-end/login (endp 25)
        front_end = get_http_target('TARGET_FRONT_END', dummy_auth)
        resp = front_end.get('/login', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        resp.assert_cssselect('p', expected_value='Cookie is set')

    @clear_session({'spanId': 7})
    def test_07_get_orders(self):
        # GET http://front-end/orders (endp 7)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/orders', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(201)

    @clear_session({'spanId': 8})
    def test_08_get_tags(self):
        # GET http://front-end/tags (endp 8)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/tags', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 27})
    def test_27_get_topbar_html(self):
        # GET http://front-end/topbar.html (endp 27)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/topbar.html', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        resp.assert_cssselect('div#top div.container div.offer a.btn.btn-success.btn-sm', expected_value='Offer of the day')


@data_driven_tests
class Tests_mockintosh(unittest.TestCase):

    @clear_session({'spanId': 17})
    def test_17_get_customers_id(self):
        # GET http://mockintosh/customers/undefined (endp 18)
        mockintosh = get_http_target('TARGET_MOCKINTOSH', authenticate)
        resp = mockintosh.get('/customers/undefined')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.lastName', expected_value='Name')
        id_ = jsonpath('$.id', resp)

        # GET http://mockintosh/customers/{id} (endp 17)
        resp = mockintosh.get(f'/customers/{id_}')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.lastName', expected_value='Name')

    # authentication-related test
    @clear_session({'spanId': 19})
    def test_19_get_login(self):
        # GET http://mockintosh/login (endp 19)
        mockintosh = get_http_target('TARGET_MOCKINTOSH', dummy_auth)
        resp = mockintosh.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')


@data_driven_tests
class Tests_orders(unittest.TestCase):

    @clear_session({'spanId': 14})
    def test_14_get_orders_search_customerId(self):
        # GET http://orders/orders/search/customerId (endp 14)
        orders = get_http_target('TARGET_ORDERS', authenticate)
        qstr = '?' + urlencode([('custId', 'undefined'), ('sort', 'date')])
        resp = orders.get('/orders/search/customerId' + qstr)
        resp.assert_status_code(200)


@data_driven_tests
class Tests_user(unittest.TestCase):

    @clear_session({'spanId': 15})
    def test_15_get_customers_id(self):
        # GET http://user/customers/{id} (endp 15)
        id_ = '57a98d98e4b00679b4a830b2'
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get(f'/customers/{id_}')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.lastName', expected_value='Name')

    # authentication-related test
    @clear_session({'spanId': 16})
    def test_16_get_login(self):
        # GET http://user/login (endp 16)
        user = get_http_target('TARGET_USER', dummy_auth)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
