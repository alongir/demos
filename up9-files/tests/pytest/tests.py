from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_carts(unittest.TestCase):

    @json_dataset('data/dataset_3.json')
    @clear_session({'spanId': 3})
    def test_03_delete_carts_id(self, data_row):
        id_, = data_row

        # DELETE http://carts/carts/{id} (endp 3)
        carts = get_http_target('TARGET_CARTS', authenticate)
        resp = carts.delete(f'/carts/{id_}')
        resp.assert_ok()
        # resp.assert_status_code(202)

    @json_dataset('data/dataset_4.json')
    @clear_session({'spanId': 4})
    def test_04_post_carts_id_items(self, data_row):
        id_, itemId, unitPrice = data_row

        # POST http://carts/carts/{id}/items (endp 4)
        carts = get_http_target('TARGET_CARTS', authenticate)
        with open('data/payload_for_endp_4.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.itemId', itemId)
        apply_into_json(json_payload, '$.unitPrice', unitPrice)
        resp = carts.post(f'/carts/{id_}/items', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(201)

    @json_dataset('data/dataset_5.json')
    @clear_session({'spanId': 5})
    def test_05_get_carts_id_merge(self, data_row):
        id_, sessionId = data_row

        # GET http://carts/carts/{id}/merge (endp 5)
        carts = get_http_target('TARGET_CARTS', authenticate)
        qstr = '?' + urlencode([('sessionId', sessionId)])
        resp = carts.get(f'/carts/{id_}/merge' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(202)

    @json_dataset('data/dataset_6.json')
    @clear_session({'spanId': 6})
    def test_06_get_carts_id_items(self, data_row):
        id_, = data_row

        # GET http://carts/carts/{id}/items (endp 6)
        carts = get_http_target('TARGET_CARTS', authenticate)
        resp = carts.get(f'/carts/{id_}/items', headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(200)


@data_driven_tests
class Tests_catalogue(unittest.TestCase):

    @json_dataset('data/dataset_1.json')
    @clear_session({'spanId': 1})
    def test_01_get_catalogue_id(self, data_row):
        id_, = data_row

        # GET http://catalogue/catalogue/{id} (endp 1)
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        resp = catalogue.get(f'/catalogue/{id_}')
        resp.assert_ok()
        # resp.assert_status_code(200)

    @json_dataset('data/dataset_2.json')
    @clear_session({'spanId': 2})
    def test_02_get_catalogue(self, data_row):
        size, = data_row

        # GET http://catalogue/catalogue (endp 2)
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        qstr = '?' + urlencode([('size', size)])
        resp = catalogue.get('/catalogue' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(200)


@data_driven_tests
class Tests_front_end(unittest.TestCase):

    @clear_session({'spanId': 9})
    def test_09_get_cart(self):
        # GET http://front-end/cart (endp 9)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/cart', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(200)

    @json_dataset('data/dataset_10.json')
    @clear_session({'spanId': 10})
    def test_10_get_catalogue(self, data_row):
        size, = data_row

        # GET http://front-end/catalogue (endp 10)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        qstr = '?' + urlencode([('size', size)])
        resp = front_end.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(200)

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
    def test_13_delete_cart(self):
        # DELETE http://front-end/cart (endp 13)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.delete('/cart')
        resp.assert_ok()
        # resp.assert_status_code(202)

    @json_dataset('data/dataset_14.json')
    @clear_session({'spanId': 14})
    def test_14_post_cart(self, data_row):
        id_, = data_row

        # POST http://front-end/cart (endp 14)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        with open('data/payload_for_endp_14.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.id', id_)
        resp = front_end.post('/cart', json=json_payload)
        resp.assert_ok()
        # resp.assert_status_code(201)

    @clear_session({'spanId': 15})
    def test_15_get_category_html(self):
        # GET http://front-end/category.html (endp 15)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/category.html')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title', expected_value='Filters ')

    @json_dataset('data/dataset_16.json')
    @clear_session({'spanId': 16})
    def test_16_get_detail_html(self, data_row):
        id_, = data_row

        # GET http://front-end/detail.html (endp 16)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        qstr = '?' + urlencode([('id', id_)])
        resp = front_end.get('/detail.html' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#content div.container div div.row.same-height-row div div.box.same-height h3', expected_value='You may also like these products')

    # authentication-related test
    @clear_session({'spanId': 17})
    def test_17_get_login(self):
        # GET http://front-end/login (endp 17)
        front_end = get_http_target('TARGET_FRONT_END', dummy_auth)
        resp = front_end.get('/login')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('p', expected_value='Cookie is set')

    @clear_session({'spanId': 18})
    def test_18_post_orders(self):
        # POST http://front-end/orders (endp 18)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.post('/orders')
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
        apply_into_json(json_payload, '$.amount', float(random.uniform(4.99, 116.979996)))
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

    @json_dataset('data/dataset_7.json')
    @clear_session({'spanId': 7})
    def test_07_get_customers_id(self, data_row):
        id_, = data_row

        # GET http://user/customers/{id} (endp 7)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get(f'/customers/{id_}')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$.lastName', expected_value='Name')

    # authentication-related test
    @clear_session({'spanId': 8})
    def test_08_get_login(self):
        # GET http://user/login (endp 8)
        user = get_http_target('TARGET_USER', dummy_auth)
        resp = user.get('/login')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$.user.lastName', expected_value='Name')
