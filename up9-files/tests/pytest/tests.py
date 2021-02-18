from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_carts(unittest.TestCase):

    @clear_session({'spanId': 19})
    def test_19_get_carts_cartId_items(self):
        # GET http://carts/carts/{cartId}/items (endp 19)
        carts = get_http_target('TARGET_CARTS', authenticate)
        resp = carts.get(f'/carts/{cartId}/items')
        resp.assert_status_code(200)

    @clear_session({'spanId': 20})
    def test_20_post_carts_cartId_items(self):
        # GET http://catalogue/catalogue (endp 17)
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        qstr = '?' + urlencode([('size', size), ('sort', 'id'), ('tags', tags)])
        resp = catalogue.get('/catalogue' + qstr)
        resp.assert_status_code(200)
        itemId = jsonpath('$.[*].id', resp)
        unitPrice = jsonpath('$.[*].price', resp)

        # POST http://carts/carts/{cartId}/items (endp 20)
        carts = get_http_target('TARGET_CARTS', authenticate)
        with open('data/payload_for_endp_20.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.itemId', itemId)
        apply_into_json(json_payload, '$.unitPrice', unitPrice)
        resp = carts.post(f'/carts/{cartId}/items', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)


@data_driven_tests
class Tests_catalogue(unittest.TestCase):

    @clear_session({'spanId': 18})
    def test_18_get_catalogue_id(self):
        # GET http://catalogue/catalogue (endp 17)
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        qstr = '?' + urlencode([('size', size), ('sort', 'id'), ('tags', tags)])
        resp = catalogue.get('/catalogue' + qstr)
        resp.assert_status_code(200)
        id_ = jsonpath('$.[*].id', resp)

        # GET http://catalogue/catalogue/{id} (endp 18)
        resp = catalogue.get(f'/catalogue/{id_}')
        resp.assert_status_code(200)


@data_driven_tests
class Tests_edge_router(unittest.TestCase):

    @clear_session({'spanId': 1})
    def test_01_get_(self):
        # GET http://edge-router/ (endp 1)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    @clear_session({'spanId': 2})
    def test_02_get_basket_html(self):
        # GET http://edge-router/basket.html (endp 2)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/basket.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#basket div.box form h1', expected_value='Shopping cart')

    @clear_session({'spanId': 4})
    def test_04_get_category_html(self):
        # GET http://edge-router/category.html (endp 4)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/category.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title', expected_value='Filters ')

    @clear_session({'spanId': 5})
    def test_05_get_detail_html(self):
        # GET http://edge-router/catalogue (endp 3)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        qstr = '?' + urlencode([('size', size)])
        resp = edge_router.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        id_ = jsonpath('$.[*].id', resp)

        # GET http://edge-router/detail.html (endp 5)
        qstr = '?' + urlencode([('id', id_)])
        resp = edge_router.get('/detail.html' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('div#content div.container div div.row.same-height-row div div.box.same-height h3', expected_value='You may also like these products')

    # authentication-related test
    @clear_session({'spanId': 6})
    def test_06_get_login(self):
        # GET http://edge-router/login (endp 6)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', dummy_auth)
        resp = edge_router.get('/login')
        resp.assert_status_code(200)
        resp.assert_cssselect('p', expected_value='Cookie is set')


@data_driven_tests
class Tests_front_end(unittest.TestCase):

    @clear_session({'spanId': 7})
    def test_07_get_(self):
        # GET http://front-end/ (endp 7)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    @clear_session({'spanId': 8})
    def test_08_get_basket_html(self):
        # GET http://front-end/basket.html (endp 8)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/basket.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#basket div.box form h1', expected_value='Shopping cart')

    @clear_session({'spanId': 10})
    def test_10_get_category_html(self):
        # GET http://front-end/category.html (endp 10)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/category.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title', expected_value='Filters ')

    @clear_session({'spanId': 11})
    def test_11_get_detail_html(self):
        # GET http://front-end/catalogue (endp 9)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        qstr = '?' + urlencode([('size', size)])
        resp = front_end.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        id_ = jsonpath('$.[*].id', resp)

        # GET http://front-end/detail.html (endp 11)
        qstr = '?' + urlencode([('id', id_)])
        resp = front_end.get('/detail.html' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('div#content div.container div div.row.same-height-row div div.box.same-height h3', expected_value='You may also like these products')

    # authentication-related test
    @clear_session({'spanId': 12})
    def test_12_get_login(self):
        # GET http://front-end/login (endp 12)
        front_end = get_http_target('TARGET_FRONT_END', dummy_auth)
        resp = front_end.get('/login')
        resp.assert_status_code(200)
        resp.assert_cssselect('p', expected_value='Cookie is set')


@data_driven_tests
class Tests_user(unittest.TestCase):

    @clear_session({'spanId': 13})
    def test_13_get_customers_id(self):
        # GET http://user/customers/{id} (endp 13)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get(f'/customers/{id_}')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.lastName', expected_value='Name')

    @clear_session({'spanId': 14})
    def test_14_get_customers_id_addresses(self):
        # GET http://user/customers/{id}/addresses (endp 14)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get(f'/customers/{id_}/addresses')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$._embedded.address.[*].city', expected_value='Glasgow')

    @clear_session({'spanId': 15})
    def test_15_get_customers_id_cards(self):
        # GET http://user/customers/{id}/cards (endp 15)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get(f'/customers/{id_}/cards')
        resp.assert_status_code(200)

    # authentication-related test
    @clear_session({'spanId': 16})
    def test_16_get_login(self):
        # GET http://user/login (endp 16)
        user = get_http_target('TARGET_USER', dummy_auth)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
