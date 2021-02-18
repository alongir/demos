from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_carts(unittest.TestCase):

    @clear_session({'spanId': 7})
    def test_07_get_carts_id_items(self):
        # GET http://carts/carts/{id}/items (endp 7)
        carts = get_http_target('TARGET_CARTS', authenticate)
        resp = carts.get(f'/carts/{id_}/items')
        resp.assert_status_code(200)

    @clear_session({'spanId': 8})
    def test_08_post_carts_id_items(self):
        # GET http://catalogue/catalogue (endp 5)
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        qstr = '?' + urlencode([('size', size), ('sort', 'id'), ('tags', tags)])
        resp = catalogue.get('/catalogue' + qstr)
        resp.assert_status_code(200)
        itemId = jsonpath('$.[*].id', resp)
        unitPrice = jsonpath('$.[*].price', resp)

        # POST http://carts/carts/{id}/items (endp 8)
        carts = get_http_target('TARGET_CARTS', authenticate)
        with open('data/payload_for_endp_8.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.itemId', itemId)
        apply_into_json(json_payload, '$.unitPrice', unitPrice)
        resp = carts.post(f'/carts/{id_}/items', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)

    @clear_session({'spanId': 27})
    def test_27_get_carts_id_merge(self):
        # GET http://carts/carts/{id}/merge (endp 27)
        carts = get_http_target('TARGET_CARTS', authenticate)
        qstr = '?' + urlencode([('sessionId', sessionId)])
        resp = carts.get(f'/carts/{id_}/merge' + qstr)
        resp.assert_status_code(202)


@data_driven_tests
class Tests_catalogue(unittest.TestCase):

    @clear_session({'spanId': 6})
    def test_06_get_catalogue_id(self):
        # GET http://catalogue/catalogue (endp 5)
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        qstr = '?' + urlencode([('size', size), ('sort', 'id'), ('tags', tags)])
        resp = catalogue.get('/catalogue' + qstr)
        resp.assert_status_code(200)
        id_ = jsonpath('$.[*].id', resp)

        # GET http://catalogue/catalogue/{id} (endp 6)
        resp = catalogue.get(f'/catalogue/{id_}')
        resp.assert_status_code(200)


@data_driven_tests
class Tests_edge_router(unittest.TestCase):

    @clear_session({'spanId': 15})
    def test_15_get_(self):
        # GET http://edge-router/ (endp 15)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    @clear_session({'spanId': 16})
    def test_16_get_basket_html(self):
        # GET http://edge-router/basket.html (endp 16)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/basket.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#basket div.box form h1', expected_value='Shopping cart')

    @clear_session({'spanId': 18})
    def test_18_get_category_html(self):
        # GET http://edge-router/category.html (endp 18)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/category.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title', expected_value='Filters ')

    @clear_session({'spanId': 19})
    def test_19_get_detail_html(self):
        # GET http://edge-router/catalogue (endp 17)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        qstr = '?' + urlencode([('size', size), ('sort', 'id'), ('tags', 'brown')])
        resp = edge_router.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        id_ = jsonpath('$.[*].id', resp)

        # GET http://edge-router/detail.html (endp 19)
        qstr = '?' + urlencode([('id', id_)])
        resp = edge_router.get('/detail.html' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('div#content div.container div div.row.same-height-row div div.box.same-height h3', expected_value='You may also like these products')

    # authentication-related test
    @clear_session({'spanId': 20})
    def test_20_get_login(self):
        # GET http://edge-router/login (endp 20)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', dummy_auth)
        resp = edge_router.get('/login')
        resp.assert_status_code(200)
        resp.assert_cssselect('p', expected_value='Cookie is set')

    @clear_session({'spanId': 23})
    def test_23_get_cart(self):
        # GET http://edge-router/cart (endp 23)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/cart', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 24})
    def test_24_get_catalogue_id(self):
        # GET http://edge-router/catalogue (endp 17)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        qstr = '?' + urlencode([('size', size), ('sort', 'id'), ('tags', 'brown')])
        resp = edge_router.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        id_ = jsonpath('$.[*].id', resp)

        # GET http://edge-router/catalogue/{id} (endp 24)
        resp = edge_router.get(f'/catalogue/{id_}', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.imageUrl.[*]', expected_value='/catalogue/images/colourful_socks.jpg')

    @clear_session({'spanId': 26})
    def test_26_get_index_html(self):
        # GET http://edge-router/index.html (endp 26)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/index.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')


@data_driven_tests
class Tests_front_end(unittest.TestCase):

    @clear_session({'spanId': 9})
    def test_09_get_(self):
        # GET http://front-end/ (endp 9)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    @clear_session({'spanId': 10})
    def test_10_get_basket_html(self):
        # GET http://front-end/basket.html (endp 10)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/basket.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#basket div.box form h1', expected_value='Shopping cart')

    @clear_session({'spanId': 12})
    def test_12_get_category_html(self):
        # GET http://front-end/category.html (endp 12)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/category.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title', expected_value='Filters ')

    @clear_session({'spanId': 13})
    def test_13_get_detail_html(self):
        # GET http://front-end/catalogue (endp 11)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        qstr = '?' + urlencode([('size', size), ('sort', 'id'), ('tags', 'brown')])
        resp = front_end.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        id_ = jsonpath('$.[*].id', resp)

        # GET http://front-end/detail.html (endp 13)
        qstr = '?' + urlencode([('id', id_)])
        resp = front_end.get('/detail.html' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('div#content div.container div div.row.same-height-row div div.box.same-height h3', expected_value='You may also like these products')

    # authentication-related test
    @clear_session({'spanId': 14})
    def test_14_get_login(self):
        # GET http://front-end/login (endp 14)
        front_end = get_http_target('TARGET_FRONT_END', dummy_auth)
        resp = front_end.get('/login')
        resp.assert_status_code(200)
        resp.assert_cssselect('p', expected_value='Cookie is set')

    @clear_session({'spanId': 21})
    def test_21_get_cart(self):
        # GET http://front-end/cart (endp 21)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/cart', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 22})
    def test_22_get_catalogue_id(self):
        # GET http://front-end/catalogue (endp 11)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        qstr = '?' + urlencode([('size', size), ('sort', 'id'), ('tags', 'brown')])
        resp = front_end.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        id_ = jsonpath('$.[*].id', resp)

        # GET http://front-end/catalogue/{id} (endp 22)
        resp = front_end.get(f'/catalogue/{id_}', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.imageUrl.[*]', expected_value='/catalogue/images/colourful_socks.jpg')


@data_driven_tests
class Tests_user(unittest.TestCase):

    @clear_session({'spanId': 1})
    def test_01_get_customers_id(self):
        # GET http://user/customers/{id} (endp 1)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get(f'/customers/{id_}')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.lastName', expected_value='Name')

    @clear_session({'spanId': 2})
    def test_02_get_customers_id_addresses(self):
        # GET http://user/customers/{id}/addresses (endp 2)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get(f'/customers/{id_}/addresses')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$._embedded.address.[*].city', expected_value='Glasgow')

    @clear_session({'spanId': 3})
    def test_03_get_customers_id_cards(self):
        # GET http://user/customers/{id}/cards (endp 3)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get(f'/customers/{id_}/cards')
        resp.assert_status_code(200)

    # authentication-related test
    @clear_session({'spanId': 4})
    def test_04_get_login(self):
        # GET http://user/login (endp 4)
        user = get_http_target('TARGET_USER', dummy_auth)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
