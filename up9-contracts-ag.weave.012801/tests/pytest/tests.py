from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_carts(unittest.TestCase):

    @clear_session({'spanId': 7})
    def test_07_delete_carts_customerId(self):
        # GET http://user/login (endp 6)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customerId = jsonpath('$.user.id', resp)

        # DELETE http://carts/carts/{customerId} (endp 7)
        carts = get_http_target('TARGET_CARTS', authenticate)
        resp = carts.delete(f'/carts/{customerId}')
        resp.assert_status_code(202)

    @json_dataset('data/dataset_8.json')
    @clear_session({'spanId': 8})
    def test_08_post_carts_customerId_items(self, data_row):
        size, = data_row

        # GET http://catalogue/catalogue (endp 1)
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        qstr = '?' + urlencode([('size', size)])
        resp = catalogue.get('/catalogue' + qstr)
        resp.assert_status_code(200)
        itemId = jsonpath('$.[*].id', resp)
        unitPrice = jsonpath('$.[*].price', resp)

        # GET http://user/login (endp 6)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customerId = jsonpath('$.user.id', resp)

        # POST http://carts/carts/{customerId}/items (endp 8)
        carts = get_http_target('TARGET_CARTS', authenticate)
        with open('data/payload_for_endp_8.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.itemId', itemId)
        apply_into_json(json_payload, '$.unitPrice', unitPrice)
        resp = carts.post(f'/carts/{customerId}/items', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)

    @json_dataset('data/dataset_9.json')
    @clear_session({'spanId': 9})
    def test_09_get_carts_customerId_merge(self, data_row):
        sessionId, = data_row

        # GET http://user/login (endp 6)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customerId = jsonpath('$.user.id', resp)

        # GET http://carts/carts/{customerId}/merge (endp 9)
        carts = get_http_target('TARGET_CARTS', authenticate)
        qstr = '?' + urlencode([('sessionId', sessionId)])
        resp = carts.get(f'/carts/{customerId}/merge' + qstr)
        resp.assert_status_code(202)

    @clear_session({'spanId': 26})
    def test_26_get_carts_customerId_items(self):
        # GET http://user/login (endp 6)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customerId = jsonpath('$.user.id', resp)

        # GET http://carts/carts/{customerId}/items (endp 26)
        carts = get_http_target('TARGET_CARTS', authenticate)
        resp = carts.get(f'/carts/{customerId}/items', headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(200)


@data_driven_tests
class Tests_catalogue(unittest.TestCase):

    @json_dataset('data/dataset_2.json')
    @clear_session({'spanId': 2})
    def test_02_get_catalogue_id(self, data_row):
        size, = data_row

        # GET http://catalogue/catalogue (endp 1)
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        qstr = '?' + urlencode([('size', size)])
        resp = catalogue.get('/catalogue' + qstr)
        resp.assert_status_code(200)
        id_ = jsonpath('$.[*].id', resp)

        # GET http://catalogue/catalogue/{id} (endp 2)
        resp = catalogue.get(f'/catalogue/{id_}')
        resp.assert_status_code(200)


@data_driven_tests
class Tests_edge_router(unittest.TestCase):

    @clear_session({'spanId': 20})
    def test_20_get_(self):
        # GET http://edge-router/ (endp 20)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    @clear_session({'spanId': 21})
    def test_21_get_basket_html(self):
        # GET http://edge-router/basket.html (endp 21)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/basket.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#basket div.box form h1', expected_value='Shopping cart')

    @clear_session({'spanId': 23})
    def test_23_get_category_html(self):
        # GET http://edge-router/category.html (endp 23)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/category.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title', expected_value='Filters ')

    @json_dataset('data/dataset_24.json')
    @clear_session({'spanId': 24})
    def test_24_get_detail_html(self, data_row):
        size, = data_row

        # GET http://edge-router/catalogue (endp 22)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        qstr = '?' + urlencode([('size', size)])
        resp = edge_router.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        id_ = jsonpath('$.[*].id', resp)

        # GET http://edge-router/detail.html (endp 24)
        qstr = '?' + urlencode([('id', id_)])
        resp = edge_router.get('/detail.html' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('div#content div.container div div.row.same-height-row div div.box.same-height h3', expected_value='You may also like these products')

    @clear_session({'spanId': 25})
    def test_25_get_login(self):
        # GET http://edge-router/login (endp 25)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/login')
        resp.assert_status_code(200)
        resp.assert_cssselect('p', expected_value='Cookie is set')

    @clear_session({'spanId': 33})
    def test_33_delete_cart(self):
        # DELETE http://edge-router/cart (endp 33)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.delete('/cart')
        resp.assert_status_code(202)

    @json_dataset('data/dataset_34.json')
    @clear_session({'spanId': 34})
    def test_34_post_cart(self, data_row):
        size, = data_row

        # GET http://edge-router/catalogue (endp 22)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        qstr = '?' + urlencode([('size', size)])
        resp = edge_router.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        id_ = jsonpath('$.[*].id', resp)

        # POST http://edge-router/cart (endp 34)
        with open('data/payload_for_endp_34.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.id', id_)
        resp = edge_router.post('/cart', json=json_payload)
        resp.assert_status_code(201)

    @clear_session({'spanId': 46})
    def test_46_get_cart(self):
        # GET http://edge-router/cart (endp 46)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/cart', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 48})
    def test_48_get_navbar_html(self):
        # GET http://edge-router/navbar.html (endp 48)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/navbar.html', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 49})
    def test_49_get_topbar_html(self):
        # GET http://edge-router/topbar.html (endp 49)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/topbar.html', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        resp.assert_cssselect('div#top div.container div.offer a.btn.btn-success.btn-sm', expected_value='Offer of the day')


@data_driven_tests
class Tests_front_end(unittest.TestCase):

    @clear_session({'spanId': 11})
    def test_11_get_(self):
        # GET http://front-end/ (endp 11)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    @clear_session({'spanId': 12})
    def test_12_get_basket_html(self):
        # GET http://front-end/basket.html (endp 12)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/basket.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#basket div.box form h1', expected_value='Shopping cart')

    @clear_session({'spanId': 14})
    def test_14_get_category_html(self):
        # GET http://front-end/category.html (endp 14)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/category.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title', expected_value='Filters ')

    @json_dataset('data/dataset_15.json')
    @clear_session({'spanId': 15})
    def test_15_get_detail_html(self, data_row):
        size, = data_row

        # GET http://front-end/catalogue (endp 13)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        qstr = '?' + urlencode([('size', size)])
        resp = front_end.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        id_ = jsonpath('$.[*].id', resp)

        # GET http://front-end/detail.html (endp 15)
        qstr = '?' + urlencode([('id', id_)])
        resp = front_end.get('/detail.html' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('div#content div.container div div.row.same-height-row div div.box.same-height h3', expected_value='You may also like these products')

    @clear_session({'spanId': 16})
    def test_16_get_login(self):
        # GET http://front-end/login (endp 16)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/login')
        resp.assert_status_code(200)
        resp.assert_cssselect('p', expected_value='Cookie is set')

    @clear_session({'spanId': 17})
    def test_17_delete_cart(self):
        # DELETE http://front-end/cart (endp 17)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.delete('/cart')
        resp.assert_status_code(202)

    @json_dataset('data/dataset_18.json')
    @clear_session({'spanId': 18})
    def test_18_post_cart(self, data_row):
        size, = data_row

        # GET http://front-end/catalogue (endp 13)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        qstr = '?' + urlencode([('size', size)])
        resp = front_end.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        id_ = jsonpath('$.[*].id', resp)

        # POST http://front-end/cart (endp 18)
        with open('data/payload_for_endp_18.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.id', id_)
        resp = front_end.post('/cart', json=json_payload)
        resp.assert_status_code(201)

    @clear_session({'spanId': 41})
    def test_41_get_cart(self):
        # GET http://front-end/cart (endp 41)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/cart', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 43})
    def test_43_get_navbar_html(self):
        # GET http://front-end/navbar.html (endp 43)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/navbar.html', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 44})
    def test_44_get_topbar_html(self):
        # GET http://front-end/topbar.html (endp 44)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/topbar.html', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        resp.assert_cssselect('div#top div.container div.offer a.btn.btn-success.btn-sm', expected_value='Offer of the day')


@data_driven_tests
class Tests_payment(unittest.TestCase):

    @json_dataset('data/dataset_29.json')
    @clear_session({'spanId': 29})
    def test_29_post_paymentAuth(self, data_row):
        addresseId, amount, cardId, ccv = data_row

        # GET http://user/addresses/{addresseId} (endp 27)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get(f'/addresses/{addresseId}', headers=dict([('accept', 'application/hal+json')]))
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.city', expected_value='Glasgow')
        country = jsonpath('$.country', resp)
        number = jsonpath('$.number', resp)
        postcode = jsonpath('$.postcode', resp)
        street = jsonpath('$.street', resp)

        # GET http://user/cards/{cardId} (endp 28)
        resp = user.get(f'/cards/{cardId}', headers=dict([('accept', 'application/hal+json')]))
        resp.assert_status_code(200)
        expires = jsonpath('$.expires', resp)
        longNum = jsonpath('$.longNum', resp)

        # POST http://payment/paymentAuth (endp 29)
        payment = get_http_target('TARGET_PAYMENT', authenticate)
        with open('data/payload_for_endp_29.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address.country', country)
        apply_into_json(json_payload, '$.address.number', number)
        apply_into_json(json_payload, '$.address.postcode', postcode)
        apply_into_json(json_payload, '$.address.street', street)
        apply_into_json(json_payload, '$.amount', amount)
        apply_into_json(json_payload, '$.card.ccv', ccv)
        apply_into_json(json_payload, '$.card.expires', expires)
        apply_into_json(json_payload, '$.card.longNum', longNum)
        resp = payment.post('/paymentAuth', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(200)


@data_driven_tests
class Tests_shipping(unittest.TestCase):

    @json_dataset('data/dataset_30.json')
    @clear_session({'spanId': 30})
    def test_30_post_shipping(self, data_row):
        name, = data_row

        # POST http://shipping/shipping (endp 30)
        shipping = get_http_target('TARGET_SHIPPING', authenticate)
        with open('data/payload_for_endp_30.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.id', str(uuid.uuid4()))
        apply_into_json(json_payload, '$.name', name)
        resp = shipping.post('/shipping', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)


@data_driven_tests
class Tests_user(unittest.TestCase):

    @clear_session({'spanId': 3})
    def test_03_get_customers_customerId(self):
        # GET http://user/login (endp 6)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customerId = jsonpath('$.user.id', resp)

        # GET http://user/customers/{customerId} (endp 3)
        resp = user.get(f'/customers/{customerId}', headers=dict([('accept', 'application/hal+json')]))
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.lastName', expected_value='Name')

    @clear_session({'spanId': 4})
    def test_04_get_customers_customerId_addresses(self):
        # GET http://user/login (endp 6)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customerId = jsonpath('$.user.id', resp)

        # GET http://user/customers/{customerId}/addresses (endp 4)
        resp = user.get(f'/customers/{customerId}/addresses')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$._embedded.address.[*].city', expected_value='Glasgow')

    @clear_session({'spanId': 5})
    def test_05_get_customers_customerId_cards(self):
        # GET http://user/login (endp 6)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customerId = jsonpath('$.user.id', resp)

        # GET http://user/customers/{customerId}/cards (endp 5)
        resp = user.get(f'/customers/{customerId}/cards')
        resp.assert_status_code(200)
