from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_carts(unittest.TestCase):

    @json_dataset('data/dataset_7.json')
    @clear_session({'spanId': 7})
    def test_07_delete_carts_customerId(self, data_row):
        customerId, = data_row

        # DELETE http://carts/carts/{customerId} (endp 7)
        carts = get_http_target('TARGET_CARTS', authenticate)
        resp = carts.delete(f'/carts/{customerId}')
        resp.assert_ok()
        # resp.assert_status_code(202)

    @json_dataset('data/dataset_8.json')
    @clear_session({'spanId': 8})
    def test_08_post_carts_customerId_items(self, data_row):
        customerId, itemId, unitPrice = data_row

        # POST http://carts/carts/{customerId}/items (endp 8)
        carts = get_http_target('TARGET_CARTS', authenticate)
        with open('data/payload_for_endp_8.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.itemId', itemId)
        apply_into_json(json_payload, '$.unitPrice', unitPrice)
        resp = carts.post(f'/carts/{customerId}/items', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(201)

    @json_dataset('data/dataset_9.json')
    @clear_session({'spanId': 9})
    def test_09_get_carts_customerId_merge(self, data_row):
        customerId, sessionId = data_row

        # GET http://carts/carts/{customerId}/merge (endp 9)
        carts = get_http_target('TARGET_CARTS', authenticate)
        qstr = '?' + urlencode([('sessionId', sessionId)])
        resp = carts.get(f'/carts/{customerId}/merge' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(202)

    @json_dataset('data/dataset_29.json')
    @clear_session({'spanId': 29})
    def test_29_get_carts_customerId_items(self, data_row):
        customerId, = data_row

        # GET http://carts/carts/{customerId}/items (endp 29)
        carts = get_http_target('TARGET_CARTS', authenticate)
        resp = carts.get(f'/carts/{customerId}/items', headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(200)


@data_driven_tests
class Tests_catalogue(unittest.TestCase):

    @clear_session({'spanId': 1})
    def test_01_get_catalogue(self):
        # GET http://catalogue/catalogue (endp 1)
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        resp = catalogue.get('/catalogue')
        resp.assert_ok()
        # resp.assert_status_code(200)

    @json_dataset('data/dataset_2.json')
    @clear_session({'spanId': 2})
    def test_02_get_catalogue_id(self, data_row):
        id_, = data_row

        # GET http://catalogue/catalogue/{id} (endp 2)
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        resp = catalogue.get(f'/catalogue/{id_}')
        resp.assert_ok()
        # resp.assert_status_code(200)


@data_driven_tests
class Tests_edge_router(unittest.TestCase):

    @clear_session({'spanId': 11})
    def test_11_get_(self):
        # GET http://edge-router/ (endp 11)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    @clear_session({'spanId': 12})
    def test_12_get_basket_html(self):
        # GET http://edge-router/basket.html (endp 12)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/basket.html')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#basket div.box form h1', expected_value='Shopping cart')

    @clear_session({'spanId': 13})
    def test_13_delete_cart(self):
        # DELETE http://edge-router/cart (endp 13)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.delete('/cart')
        resp.assert_ok()
        # resp.assert_status_code(202)

    @json_dataset('data/dataset_14.json')
    @clear_session({'spanId': 14})
    def test_14_post_cart(self, data_row):
        id_, = data_row

        # POST http://edge-router/cart (endp 14)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        with open('data/payload_for_endp_14.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.id', id_)
        resp = edge_router.post('/cart', json=json_payload)
        resp.assert_ok()
        # resp.assert_status_code(201)

    @clear_session({'spanId': 15})
    def test_15_get_catalogue(self):
        # GET http://edge-router/catalogue (endp 15)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/catalogue')
        resp.assert_ok()
        # resp.assert_status_code(200)

    @clear_session({'spanId': 16})
    def test_16_get_category_html(self):
        # GET http://edge-router/category.html (endp 16)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/category.html')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title', expected_value='Filters ')

    @json_dataset('data/dataset_17.json')
    @clear_session({'spanId': 17})
    def test_17_get_detail_html(self, data_row):
        id_, = data_row

        # GET http://edge-router/detail.html (endp 17)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        qstr = '?' + urlencode([('id', id_)])
        resp = edge_router.get('/detail.html' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#content div.container div div.row.same-height-row div div.box.same-height h3', expected_value='You may also like these products')

    # authentication-related test
    @clear_session({'spanId': 18})
    def test_18_get_login(self):
        # GET http://edge-router/login (endp 18)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', dummy_auth)
        resp = edge_router.get('/login')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('p', expected_value='Cookie is set')

    @clear_session({'spanId': 19})
    def test_19_post_orders(self):
        # POST http://edge-router/orders (endp 19)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.post('/orders')
        resp.assert_ok()
        # resp.assert_status_code(201)
        # resp.assert_jsonpath('$.address.city', expected_value='Glasgow')


@data_driven_tests
class Tests_front_end(unittest.TestCase):

    @clear_session({'spanId': 20})
    def test_20_get_(self):
        # GET http://front-end/ (endp 20)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    @clear_session({'spanId': 21})
    def test_21_get_basket_html(self):
        # GET http://front-end/basket.html (endp 21)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/basket.html')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#basket div.box form h1', expected_value='Shopping cart')

    @clear_session({'spanId': 22})
    def test_22_get_catalogue(self):
        # GET http://front-end/catalogue (endp 22)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/catalogue')
        resp.assert_ok()
        # resp.assert_status_code(200)

    @clear_session({'spanId': 23})
    def test_23_get_category_html(self):
        # GET http://front-end/category.html (endp 23)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/category.html')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title', expected_value='Filters ')

    @json_dataset('data/dataset_24.json')
    @clear_session({'spanId': 24})
    def test_24_get_detail_html(self, data_row):
        id_, = data_row

        # GET http://front-end/detail.html (endp 24)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        qstr = '?' + urlencode([('id', id_)])
        resp = front_end.get('/detail.html' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#content div.container div div.row.same-height-row div div.box.same-height h3', expected_value='You may also like these products')

    # authentication-related test
    @clear_session({'spanId': 25})
    def test_25_get_login(self):
        # GET http://front-end/login (endp 25)
        front_end = get_http_target('TARGET_FRONT_END', dummy_auth)
        resp = front_end.get('/login')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('p', expected_value='Cookie is set')

    @clear_session({'spanId': 26})
    def test_26_delete_cart(self):
        # DELETE http://front-end/cart (endp 26)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.delete('/cart')
        resp.assert_ok()
        # resp.assert_status_code(202)

    @json_dataset('data/dataset_27.json')
    @clear_session({'spanId': 27})
    def test_27_post_cart(self, data_row):
        id_, = data_row

        # POST http://front-end/cart (endp 27)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        with open('data/payload_for_endp_27.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.id', id_)
        resp = front_end.post('/cart', json=json_payload)
        resp.assert_ok()
        # resp.assert_status_code(201)

    @clear_session({'spanId': 28})
    def test_28_post_orders(self):
        # POST http://front-end/orders (endp 28)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.post('/orders')
        resp.assert_ok()
        # resp.assert_status_code(201)
        # resp.assert_jsonpath('$.address.city', expected_value='Glasgow')


@data_driven_tests
class Tests_orders(unittest.TestCase):

    @json_dataset('data/dataset_10.json')
    @clear_session({'spanId': 10})
    def test_10_post_orders(self, data_row):
        address, card, customer, items = data_row

        # POST http://orders/orders (endp 10)
        orders = get_http_target('TARGET_ORDERS', authenticate)
        with open('data/payload_for_endp_10.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address', address)
        apply_into_json(json_payload, '$.card', card)
        apply_into_json(json_payload, '$.customer', customer)
        apply_into_json(json_payload, '$.items', items)
        resp = orders.post('/orders', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(201)
        # resp.assert_jsonpath('$.address.city', expected_value='Glasgow')


@data_driven_tests
class Tests_payment(unittest.TestCase):

    @json_dataset('data/dataset_32.json')
    @clear_session({'spanId': 32})
    def test_32_post_paymentAuth(self, data_row):
        amount, ccv, country, expires, longNum, number, postcode, street = data_row

        # POST http://payment/paymentAuth (endp 32)
        payment = get_http_target('TARGET_PAYMENT', authenticate)
        with open('data/payload_for_endp_32.json', 'r') as json_payload_file:
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
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$.message', expected_value='Payment authorised')


@data_driven_tests
class Tests_shipping(unittest.TestCase):

    @json_dataset('data/dataset_33.json')
    @clear_session({'spanId': 33})
    def test_33_post_shipping(self, data_row):
        name, = data_row

        # POST http://shipping/shipping (endp 33)
        shipping = get_http_target('TARGET_SHIPPING', authenticate)
        with open('data/payload_for_endp_33.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.id', str(uuid.uuid4()))
        apply_into_json(json_payload, '$.name', name)
        resp = shipping.post('/shipping', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(201)


@data_driven_tests
class Tests_user(unittest.TestCase):

    @json_dataset('data/dataset_3.json')
    @clear_session({'spanId': 3})
    def test_03_get_customers_customerId(self, data_row):
        customerId, = data_row

        # GET http://user/customers/{customerId} (endp 3)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get(f'/customers/{customerId}', headers=dict([('accept', 'application/hal+json')]))
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$.lastName', expected_value='Name')

    @json_dataset('data/dataset_4.json')
    @clear_session({'spanId': 4})
    def test_04_get_customers_customerId_addresses(self, data_row):
        customerId, = data_row

        # GET http://user/customers/{customerId}/addresses (endp 4)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get(f'/customers/{customerId}/addresses')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$._embedded.address.[*].city', expected_value='Glasgow')

    @json_dataset('data/dataset_5.json')
    @clear_session({'spanId': 5})
    def test_05_get_customers_customerId_cards(self, data_row):
        customerId, = data_row

        # GET http://user/customers/{customerId}/cards (endp 5)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get(f'/customers/{customerId}/cards')
        resp.assert_ok()
        # resp.assert_status_code(200)

    # authentication-related test
    @clear_session({'spanId': 6})
    def test_06_get_login(self):
        # GET http://user/login (endp 6)
        user = get_http_target('TARGET_USER', dummy_auth)
        resp = user.get('/login')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$.user.lastName', expected_value='Name')

    @json_dataset('data/dataset_30.json')
    @clear_session({'spanId': 30})
    def test_30_get_addresses_addresseId(self, data_row):
        addresseId, = data_row

        # GET http://user/addresses/{addresseId} (endp 30)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get(f'/addresses/{addresseId}', headers=dict([('accept', 'application/hal+json')]))
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$.city', expected_value='Glasgow')

    @json_dataset('data/dataset_31.json')
    @clear_session({'spanId': 31})
    def test_31_get_cards_cardId(self, data_row):
        cardId, = data_row

        # GET http://user/cards/{cardId} (endp 31)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get(f'/cards/{cardId}', headers=dict([('accept', 'application/hal+json')]))
        resp.assert_ok()
        # resp.assert_status_code(200)
