from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_carts_sock_shop(unittest.TestCase):

    @json_dataset('data/dataset_6.json')
    @clear_session({'spanId': 6})
    def test_06_delete_carts_customerId(self, data_row):
        customerId, = data_row

        # DELETE http://carts.sock-shop/carts/{customerId} (endp 6)
        carts_sock_shop = get_http_client('http://carts.sock-shop', authenticate)
        resp = carts_sock_shop.delete(f'/carts/{customerId}')
        resp.assert_ok()
        # resp.assert_status_code(202)

    @json_dataset('data/dataset_7.json')
    @clear_session({'spanId': 7})
    def test_07_post_carts_customerId_items(self, data_row):
        customerId, itemId, unitPrice = data_row

        # POST http://carts.sock-shop/carts/{customerId}/items (endp 7)
        carts_sock_shop = get_http_client('http://carts.sock-shop', authenticate)
        with open('data/payload_for_endp_7.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.itemId', itemId)
        apply_into_json(json_payload, '$.unitPrice', unitPrice)
        resp = carts_sock_shop.post(f'/carts/{customerId}/items', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(201)

    @json_dataset('data/dataset_9.json')
    @clear_session({'spanId': 9})
    def test_09_get_carts_customerId_items(self, data_row):
        customerId, = data_row

        # GET http://carts.sock-shop/carts/{customerId}/items (endp 9)
        carts_sock_shop = get_http_client('http://carts.sock-shop', authenticate)
        resp = carts_sock_shop.get(f'/carts/{customerId}/items', headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(200)

    @json_dataset('data/dataset_8.json')
    @clear_session({'spanId': 8})
    def test_08_get_carts_customerId_merge(self, data_row):
        customerId, sessionId = data_row

        # GET http://carts.sock-shop/carts/{customerId}/merge (endp 8)
        carts_sock_shop = get_http_client('http://carts.sock-shop', authenticate)
        qstr = '?' + urlencode([('sessionId', sessionId)])
        resp = carts_sock_shop.get(f'/carts/{customerId}/merge' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(202)


@data_driven_tests
class Tests_catalogue_sock_shop(unittest.TestCase):

    @json_dataset('data/dataset_2.json')
    @clear_session({'spanId': 2})
    def test_02_get_catalogue(self, data_row):
        size, tags = data_row

        # GET http://catalogue.sock-shop/catalogue (endp 2)
        catalogue_sock_shop = get_http_client('http://catalogue.sock-shop', authenticate)
        qstr = '?' + urlencode([('page', '1'), ('size', size), ('tags', tags)])
        resp = catalogue_sock_shop.get('/catalogue' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(200)

    @json_dataset('data/dataset_3.json')
    @clear_session({'spanId': 3})
    def test_03_get_catalogue_size(self, data_row):
        tags, = data_row

        # GET http://catalogue.sock-shop/catalogue/size (endp 3)
        catalogue_sock_shop = get_http_client('http://catalogue.sock-shop', authenticate)
        qstr = '?' + urlencode([('tags', tags)])
        resp = catalogue_sock_shop.get('/catalogue/size' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(200)

    @clear_session({'spanId': 5})
    def test_05_get_tags(self):
        # GET http://catalogue.sock-shop/tags (endp 5)
        catalogue_sock_shop = get_http_client('http://catalogue.sock-shop', authenticate)
        resp = catalogue_sock_shop.get('/tags')
        resp.assert_ok()
        # resp.assert_status_code(200)


@data_driven_tests
class Tests_front_end_sock_shop(unittest.TestCase):

    @clear_session({'spanId': 1})
    def test_01_get_(self):
        # GET http://front-end.sock-shop/ (endp 1)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        resp = front_end_sock_shop.get('/')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    @clear_session({'spanId': 22})
    def test_22_get_cart(self):
        # GET http://front-end.sock-shop/cart (endp 22)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        resp = front_end_sock_shop.get('/cart', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(200)

    @json_dataset('data/dataset_23.json')
    @clear_session({'spanId': 23})
    def test_23_get_catalogue(self, data_row):
        size, tags = data_row

        # GET http://front-end.sock-shop/catalogue (endp 23)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        qstr = '?' + urlencode([('page', '1'), ('size', size), ('tags', tags)])
        resp = front_end_sock_shop.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(200)

    @json_dataset('data/dataset_25.json')
    @clear_session({'spanId': 25})
    def test_25_get_catalogue_size(self, data_row):
        tags, = data_row

        # GET http://front-end.sock-shop/catalogue/size (endp 25)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        qstr = '?' + urlencode([('tags', tags)])
        resp = front_end_sock_shop.get('/catalogue/size' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(200)

    @clear_session({'spanId': 27})
    def test_27_get_category_html(self):
        # GET http://front-end.sock-shop/category.html (endp 27)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        qstr = '?' + urlencode([('tags', 'black')])
        resp = front_end_sock_shop.get('/category.html' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title', expected_value='Filters ')

    @clear_session({'spanId': 28})
    def test_28_get_tags(self):
        # GET http://front-end.sock-shop/tags (endp 28)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        resp = front_end_sock_shop.get('/tags', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(200)


@data_driven_tests
class Tests_orders_sock_shop(unittest.TestCase):

    @json_dataset('data/dataset_15.json')
    @clear_session({'spanId': 15})
    def test_15_post_orders(self, data_row):
        address, card, customer, items = data_row

        # POST http://orders.sock-shop/orders (endp 15)
        orders_sock_shop = get_http_client('http://orders.sock-shop', authenticate)
        with open('data/payload_for_endp_15.json', 'r') as json_payload_file:
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

    @json_dataset('data/dataset_20.json')
    @clear_session({'spanId': 20})
    def test_20_post_paymentAuth(self, data_row):
        ccv, country, expires, longNum, number, postcode, street = data_row

        # POST http://payment.sock-shop/paymentAuth (endp 20)
        payment_sock_shop = get_http_client('http://payment.sock-shop', authenticate)
        with open('data/payload_for_endp_20.json', 'r') as json_payload_file:
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

    @json_dataset('data/dataset_21.json')
    @clear_session({'spanId': 21})
    def test_21_post_shipping(self, data_row):
        name, = data_row

        # POST http://shipping.sock-shop/shipping (endp 21)
        shipping_sock_shop = get_http_client('http://shipping.sock-shop', authenticate)
        with open('data/payload_for_endp_21.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.id', str(uuid.uuid4()))
        apply_into_json(json_payload, '$.name', name)
        resp = shipping_sock_shop.post('/shipping', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(201)


@data_driven_tests
class Tests_user_sock_shop(unittest.TestCase):

    @json_dataset('data/dataset_17.json')
    @clear_session({'spanId': 17})
    def test_17_get_addresses_addresseId(self, data_row):
        addresseId, = data_row

        # GET http://user.sock-shop/addresses/{addresseId} (endp 17)
        user_sock_shop = get_http_client('http://user.sock-shop', authenticate)
        resp = user_sock_shop.get(f'/addresses/{addresseId}', headers=dict([('accept', 'application/hal+json')]))
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$.city', expected_value='Glasgow')

    @json_dataset('data/dataset_18.json')
    @clear_session({'spanId': 18})
    def test_18_get_cards_cardId(self, data_row):
        cardId, = data_row

        # GET http://user.sock-shop/cards/{cardId} (endp 18)
        user_sock_shop = get_http_client('http://user.sock-shop', authenticate)
        resp = user_sock_shop.get(f'/cards/{cardId}', headers=dict([('accept', 'application/hal+json')]))
        resp.assert_ok()
        # resp.assert_status_code(200)

    @json_dataset('data/dataset_11.json')
    @clear_session({'spanId': 11})
    def test_11_get_customers_id(self, data_row):
        id_, = data_row

        # GET http://user.sock-shop/customers/{id} (endp 11)
        user_sock_shop = get_http_client('http://user.sock-shop', authenticate)
        resp = user_sock_shop.get(f'/customers/{id_}', headers=dict([('accept', 'application/hal+json')]))
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$.lastName', expected_value='Name')

    @json_dataset('data/dataset_12.json')
    @clear_session({'spanId': 12})
    def test_12_get_customers_id_addresses(self, data_row):
        id_, = data_row

        # GET http://user.sock-shop/customers/{id}/addresses (endp 12)
        user_sock_shop = get_http_client('http://user.sock-shop', authenticate)
        resp = user_sock_shop.get(f'/customers/{id_}/addresses')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$._embedded.address[*].city', expected_value='Glasgow')

    @json_dataset('data/dataset_13.json')
    @clear_session({'spanId': 13})
    def test_13_get_customers_id_cards(self, data_row):
        id_, = data_row

        # GET http://user.sock-shop/customers/{id}/cards (endp 13)
        user_sock_shop = get_http_client('http://user.sock-shop', authenticate)
        resp = user_sock_shop.get(f'/customers/{id_}/cards')
        resp.assert_ok()
        # resp.assert_status_code(200)

    @clear_session({'spanId': 14})
    def test_14_get_login(self):
        # GET http://user.sock-shop/login (endp 14)
        user_sock_shop = get_http_client('http://user.sock-shop', authenticate)
        resp = user_sock_shop.get('/login')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$.user.lastName', expected_value='Name')
