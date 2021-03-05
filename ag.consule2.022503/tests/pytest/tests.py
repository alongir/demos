from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_carts(unittest.TestCase):

    @json_dataset('data/dataset_14.json')
    @clear_session({'spanId': 14})
    def test_14_delete_carts_customerId(self, data_row):
        customerId, = data_row

        # DELETE http://carts/carts/{customerId} (endp 14)
        carts = get_http_target('TARGET_CARTS', authenticate)
        resp = carts.delete(f'/carts/{customerId}')
        resp.assert_ok()
        # resp.assert_status_code(202)

    @json_dataset('data/dataset_15.json')
    @clear_session({'spanId': 15})
    def test_15_post_carts_customerId_items(self, data_row):
        customerId, itemId, unitPrice = data_row

        # POST http://carts/carts/{customerId}/items (endp 15)
        carts = get_http_target('TARGET_CARTS', authenticate)
        with open('data/payload_for_endp_15.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.itemId', itemId)
        apply_into_json(json_payload, '$.unitPrice', unitPrice)
        resp = carts.post(f'/carts/{customerId}/items', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(201)

    @json_dataset('data/dataset_16.json')
    @clear_session({'spanId': 16})
    def test_16_get_carts_customerId_merge(self, data_row):
        customerId, sessionId = data_row

        # GET http://carts/carts/{customerId}/merge (endp 16)
        carts = get_http_target('TARGET_CARTS', authenticate)
        qstr = '?' + urlencode([('sessionId', sessionId)])
        resp = carts.get(f'/carts/{customerId}/merge' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(202)

    @json_dataset('data/dataset_18.json')
    @clear_session({'spanId': 18})
    def test_18_get_carts_customerId_items(self, data_row):
        customerId, = data_row

        # GET http://carts/carts/{customerId}/items (endp 18)
        carts = get_http_target('TARGET_CARTS', authenticate)
        resp = carts.get(f'/carts/{customerId}/items', headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(200)


@data_driven_tests
class Tests_catalogue(unittest.TestCase):

    @json_dataset('data/dataset_10.json')
    @clear_session({'spanId': 10})
    def test_10_get_catalogue(self, data_row):
        size, = data_row

        # GET http://catalogue/catalogue (endp 10)
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        qstr = '?' + urlencode([('size', size)])
        resp = catalogue.get('/catalogue' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(200)

    @json_dataset('data/dataset_11.json')
    @clear_session({'spanId': 11})
    def test_11_get_catalogue_id(self, data_row):
        id_, = data_row

        # GET http://catalogue/catalogue/{id} (endp 11)
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        resp = catalogue.get(f'/catalogue/{id_}')
        resp.assert_ok()
        # resp.assert_status_code(200)


@data_driven_tests
class Tests_front_end(unittest.TestCase):

    @clear_session({'spanId': 1})
    def test_01_get_(self):
        # GET http://front-end/ (endp 1)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    @clear_session({'spanId': 2})
    def test_02_delete_cart(self):
        # DELETE http://front-end/cart (endp 2)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.delete('/cart')
        resp.assert_ok()
        # resp.assert_status_code(202)

    @json_dataset('data/dataset_3.json')
    @clear_session({'spanId': 3})
    def test_03_post_cart(self, data_row):
        id_, = data_row

        # POST http://front-end/cart (endp 3)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        with open('data/payload_for_endp_3.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.id', id_)
        resp = front_end.post('/cart', json=json_payload)
        resp.assert_ok()
        # resp.assert_status_code(201)

    @json_dataset('data/dataset_4.json')
    @clear_session({'spanId': 4})
    def test_04_get_catalogue(self, data_row):
        size, = data_row

        # GET http://front-end/catalogue (endp 4)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        qstr = '?' + urlencode([('size', size)])
        resp = front_end.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(200)

    @clear_session({'spanId': 5})
    def test_05_get_category_html(self):
        # GET http://front-end/category.html (endp 5)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/category.html')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title', expected_value='Filters ')

    @clear_session({'spanId': 6})
    def test_06_get_detail_html(self):
        # GET http://front-end/detail.html (endp 6)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        qstr = '?' + urlencode([('id', str(uuid.uuid4()))])
        resp = front_end.get('/detail.html' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#content div.container div div.row.same-height-row div div.box.same-height h3', expected_value='You may also like these products')

    # authentication-related test
    @clear_session({'spanId': 7})
    def test_07_get_login(self):
        # GET http://front-end/login (endp 7)
        front_end = get_http_target('TARGET_FRONT_END', dummy_auth)
        resp = front_end.get('/login')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('p', expected_value='Cookie is set')

    @clear_session({'spanId': 8})
    def test_08_get_basket_html(self):
        # GET http://front-end/basket.html (endp 8)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/basket.html')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#basket div.box form h1', expected_value='Shopping cart')

    @clear_session({'spanId': 9})
    def test_09_post_orders(self):
        # POST http://front-end/orders (endp 9)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.post('/orders')
        resp.assert_ok()
        # resp.assert_status_code(201)
        # resp.assert_jsonpath('$.address.city', expected_value='Glasgow')

    @clear_session({'spanId': 21})
    def test_21_get_cart(self):
        # GET http://front-end/cart (endp 21)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/cart', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(200)


@data_driven_tests
class Tests_orders(unittest.TestCase):

    @json_dataset('data/dataset_17.json')
    @clear_session({'spanId': 17})
    def test_17_post_orders(self, data_row):
        address, card, customer, items = data_row

        # POST http://orders/orders (endp 17)
        orders = get_http_target('TARGET_ORDERS', authenticate)
        with open('data/payload_for_endp_17.json', 'r') as json_payload_file:
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

    @json_dataset('data/dataset_19.json')
    @clear_session({'spanId': 19})
    def test_19_post_paymentAuth(self, data_row):
        ccv, country, expires, id_, id1, id2, longNum, number, postcode, street = data_row

        # POST http://payment/paymentAuth (endp 19)
        payment = get_http_target('TARGET_PAYMENT', authenticate)
        with open('data/payload_for_endp_19.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address.country', country)
        apply_into_json(json_payload, '$.address.id', id_)
        apply_into_json(json_payload, '$.address.number', number)
        apply_into_json(json_payload, '$.address.postcode', postcode)
        apply_into_json(json_payload, '$.address.street', street)
        apply_into_json(json_payload, '$.amount', float(random.uniform(4.99, 47.989998)))
        apply_into_json(json_payload, '$.card.ccv', ccv)
        apply_into_json(json_payload, '$.card.expires', expires)
        apply_into_json(json_payload, '$.card.id', id1)
        apply_into_json(json_payload, '$.card.longNum', longNum)
        apply_into_json(json_payload, '$.customer.id', id2)
        resp = payment.post('/paymentAuth', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(200)


@data_driven_tests
class Tests_shipping(unittest.TestCase):

    @json_dataset('data/dataset_20.json')
    @clear_session({'spanId': 20})
    def test_20_post_shipping(self, data_row):
        name, = data_row

        # POST http://shipping/shipping (endp 20)
        shipping = get_http_target('TARGET_SHIPPING', authenticate)
        with open('data/payload_for_endp_20.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.id', str(uuid.uuid4()))
        apply_into_json(json_payload, '$.name', name)
        resp = shipping.post('/shipping', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(201)


@data_driven_tests
class Tests_user(unittest.TestCase):

    @json_dataset('data/dataset_12.json')
    @clear_session({'spanId': 12})
    def test_12_get_customers_id(self, data_row):
        id_, = data_row

        # GET http://user/customers/{id} (endp 12)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get(f'/customers/{id_}')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$.lastName', expected_value='Name')

    # authentication-related test
    @clear_session({'spanId': 13})
    def test_13_get_login(self):
        # GET http://user/login (endp 13)
        user = get_http_target('TARGET_USER', dummy_auth)
        resp = user.get('/login')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$.user.lastName', expected_value='Name')
