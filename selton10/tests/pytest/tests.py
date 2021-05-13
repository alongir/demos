from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_carts_sock_shop(unittest.TestCase):

    @json_dataset('data/dataset_2.json')
    @clear_session({'spanId': 2})
    def test_02_delete_carts_customerId(self, data_row):
        customerId, = data_row

        # DELETE http://carts.sock-shop/carts/{customerId} (endp 2)
        carts_sock_shop = get_http_client('http://carts.sock-shop', authenticate)
        resp = carts_sock_shop.delete(f'/carts/{customerId}')
        resp.assert_ok()
        # resp.assert_status_code(202)

    @json_dataset('data/dataset_3.json')
    @clear_session({'spanId': 3})
    def test_03_post_carts_customerId_items(self, data_row):
        customerId, itemId, unitPrice = data_row

        # POST http://carts.sock-shop/carts/{customerId}/items (endp 3)
        carts_sock_shop = get_http_client('http://carts.sock-shop', authenticate)
        with open('data/payload_for_endp_3.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.itemId', itemId)
        apply_into_json(json_payload, '$.unitPrice', unitPrice)
        resp = carts_sock_shop.post(f'/carts/{customerId}/items', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(201)

    @json_dataset('data/dataset_5.json')
    @clear_session({'spanId': 5})
    def test_05_get_carts_customerId_items(self, data_row):
        customerId, = data_row

        # GET http://carts.sock-shop/carts/{customerId}/items (endp 5)
        carts_sock_shop = get_http_client('http://carts.sock-shop', authenticate)
        resp = carts_sock_shop.get(f'/carts/{customerId}/items', headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(200)

    @json_dataset('data/dataset_4.json')
    @clear_session({'spanId': 4})
    def test_04_get_carts_customerId_merge(self, data_row):
        customerId, sessionId = data_row

        # GET http://carts.sock-shop/carts/{customerId}/merge (endp 4)
        carts_sock_shop = get_http_client('http://carts.sock-shop', authenticate)
        qstr = '?' + urlencode([('sessionId', sessionId)])
        resp = carts_sock_shop.get(f'/carts/{customerId}/merge' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(202)


@data_driven_tests
class Tests_catalogue_sock_shop(unittest.TestCase):

    @json_dataset('data/dataset_1.json')
    @clear_session({'spanId': 1})
    def test_01_get_catalogue(self, data_row):
        size, = data_row

        # GET http://catalogue.sock-shop/catalogue (endp 1)
        catalogue_sock_shop = get_http_client('http://catalogue.sock-shop', authenticate)
        qstr = '?' + urlencode([('size', size)])
        resp = catalogue_sock_shop.get('/catalogue' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(200)


@data_driven_tests
class Tests_front_end_sock_shop(unittest.TestCase):

    @clear_session({'spanId': 11})
    def test_11_get_(self):
        # GET http://front-end.sock-shop/ (endp 11)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        resp = front_end_sock_shop.get('/')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')


@data_driven_tests
class Tests_orders_sock_shop(unittest.TestCase):

    @json_dataset('data/dataset_10.json')
    @clear_session({'spanId': 10})
    def test_10_post_orders(self, data_row):
        address, card, customer, items = data_row

        # POST http://orders.sock-shop/orders (endp 10)
        orders_sock_shop = get_http_client('http://orders.sock-shop', authenticate)
        with open('data/payload_for_endp_10.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address', address)
        apply_into_json(json_payload, '$.card', card)
        apply_into_json(json_payload, '$.customer', customer)
        apply_into_json(json_payload, '$.items', items)
        resp = orders_sock_shop.post('/orders', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(201)
        # resp.assert_jsonpath('$.address.city', expected_value='Glasgow')


@data_driven_tests
class Tests_payment_sock_shop(unittest.TestCase):

    @json_dataset('data/dataset_16.json')
    @clear_session({'spanId': 16})
    def test_16_post_paymentAuth(self, data_row):
        ccv, country, expires, longNum, number, postcode, street = data_row

        # POST http://payment.sock-shop/paymentAuth (endp 16)
        payment_sock_shop = get_http_client('http://payment.sock-shop', authenticate)
        with open('data/payload_for_endp_16.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address.country', country)
        apply_into_json(json_payload, '$.address.number', number)
        apply_into_json(json_payload, '$.address.postcode', postcode)
        apply_into_json(json_payload, '$.address.street', street)
        apply_into_json(json_payload, '$.amount', float(random.uniform(4.99, 350.94)))
        apply_into_json(json_payload, '$.card.ccv', ccv)
        apply_into_json(json_payload, '$.card.expires', expires)
        apply_into_json(json_payload, '$.card.longNum', longNum)
        resp = payment_sock_shop.post('/paymentAuth', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(200)


@data_driven_tests
class Tests_shipping_sock_shop(unittest.TestCase):

    @json_dataset('data/dataset_17.json')
    @clear_session({'spanId': 17})
    def test_17_post_shipping(self, data_row):
        name, = data_row

        # POST http://shipping.sock-shop/shipping (endp 17)
        shipping_sock_shop = get_http_client('http://shipping.sock-shop', authenticate)
        with open('data/payload_for_endp_17.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.id', str(uuid.uuid4()))
        apply_into_json(json_payload, '$.name', name)
        resp = shipping_sock_shop.post('/shipping', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(201)


@data_driven_tests
class Tests_user_sock_shop(unittest.TestCase):

    @json_dataset('data/dataset_13.json')
    @clear_session({'spanId': 13})
    def test_13_get_addresses_addresseId(self, data_row):
        addresseId, = data_row

        # GET http://user.sock-shop/addresses/{addresseId} (endp 13)
        user_sock_shop = get_http_client('http://user.sock-shop', authenticate)
        resp = user_sock_shop.get(f'/addresses/{addresseId}', headers=dict([('accept', 'application/hal+json')]))
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$.city', expected_value='Glasgow')

    @json_dataset('data/dataset_14.json')
    @clear_session({'spanId': 14})
    def test_14_get_cards_cardId(self, data_row):
        cardId, = data_row

        # GET http://user.sock-shop/cards/{cardId} (endp 14)
        user_sock_shop = get_http_client('http://user.sock-shop', authenticate)
        resp = user_sock_shop.get(f'/cards/{cardId}', headers=dict([('accept', 'application/hal+json')]))
        resp.assert_ok()
        # resp.assert_status_code(200)

    @json_dataset('data/dataset_6.json')
    @clear_session({'spanId': 6})
    def test_06_get_customers_id(self, data_row):
        id_, = data_row

        # GET http://user.sock-shop/customers/{id} (endp 6)
        user_sock_shop = get_http_client('http://user.sock-shop', authenticate)
        resp = user_sock_shop.get(f'/customers/{id_}', headers=dict([('accept', 'application/hal+json')]))
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$.lastName', expected_value='Name')

    @json_dataset('data/dataset_7.json')
    @clear_session({'spanId': 7})
    def test_07_get_customers_id_addresses(self, data_row):
        id_, = data_row

        # GET http://user.sock-shop/customers/{id}/addresses (endp 7)
        user_sock_shop = get_http_client('http://user.sock-shop', authenticate)
        resp = user_sock_shop.get(f'/customers/{id_}/addresses')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$._embedded.address[*].city', expected_value='Glasgow')

    @json_dataset('data/dataset_8.json')
    @clear_session({'spanId': 8})
    def test_08_get_customers_id_cards(self, data_row):
        id_, = data_row

        # GET http://user.sock-shop/customers/{id}/cards (endp 8)
        user_sock_shop = get_http_client('http://user.sock-shop', authenticate)
        resp = user_sock_shop.get(f'/customers/{id_}/cards')
        resp.assert_ok()
        # resp.assert_status_code(200)

    @clear_session({'spanId': 9})
    def test_09_get_login(self):
        # GET http://user.sock-shop/login (endp 9)
        user_sock_shop = get_http_client('http://user.sock-shop', authenticate)
        resp = user_sock_shop.get('/login')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$.user.lastName', expected_value='Name')
