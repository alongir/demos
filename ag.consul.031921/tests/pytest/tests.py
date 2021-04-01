from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_carts(unittest.TestCase):

    @clear_session({'spanId': 22})
    def test_22_delete_carts_customerId(self):
        # GET http://user/login (endp 32)
        user = get_http_client('http://user', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customerId = jsonpath('$.user.id', resp)

        # DELETE http://carts/carts/{customerId} (endp 22)
        carts = get_http_client('http://carts', authenticate)
        resp = carts.delete(f'/carts/{customerId}')
        resp.assert_status_code(202)

    @json_dataset('data/dataset_23.json')
    @clear_session({'spanId': 23})
    def test_23_post_carts_customerId_items(self, data_row):
        size, = data_row

        # GET http://catalogue/tags (endp 30)
        catalogue = get_http_client('http://catalogue', authenticate)
        resp = catalogue.get('/tags')
        resp.assert_status_code(200)
        tags = jsonpath('$.tags[*]', resp)

        # GET http://catalogue/catalogue (endp 29)
        qstr = '?' + urlencode([('page', '1'), ('size', size), ('sort', 'id'), ('tags', tags)])
        resp = catalogue.get('/catalogue' + qstr)
        resp.assert_status_code(200)
        itemId = jsonpath('$[*].id', resp)
        unitPrice = jsonpath('$[*].price', resp)

        # GET http://user/login (endp 32)
        user = get_http_client('http://user', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customerId = jsonpath('$.user.id', resp)

        # POST http://carts/carts/{customerId}/items (endp 23)
        carts = get_http_client('http://carts', authenticate)
        with open('data/payload_for_endp_23.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.itemId', itemId)
        apply_into_json(json_payload, '$.unitPrice', unitPrice)
        resp = carts.post(f'/carts/{customerId}/items', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)

    @clear_session({'spanId': 26})
    def test_26_get_carts_customerId_items(self):
        # GET http://user/login (endp 32)
        user = get_http_client('http://user', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customerId = jsonpath('$.user.id', resp)

        # GET http://carts/carts/{customerId}/items (endp 26)
        carts = get_http_client('http://carts', authenticate)
        resp = carts.get(f'/carts/{customerId}/items', headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(200)

    @json_dataset('data/dataset_24.json')
    @clear_session({'spanId': 24})
    def test_24_get_carts_customerId_merge(self, data_row):
        sessionId, = data_row

        # GET http://user/login (endp 32)
        user = get_http_client('http://user', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customerId = jsonpath('$.user.id', resp)

        # GET http://carts/carts/{customerId}/merge (endp 24)
        carts = get_http_client('http://carts', authenticate)
        qstr = '?' + urlencode([('sessionId', sessionId)])
        resp = carts.get(f'/carts/{customerId}/merge' + qstr)
        resp.assert_status_code(202)


@data_driven_tests
class Tests_catalogue(unittest.TestCase):

    @json_dataset('data/dataset_27.json')
    @clear_session({'spanId': 27})
    def test_27_get_catalogue_id(self, data_row):
        size, = data_row

        # GET http://catalogue/tags (endp 30)
        catalogue = get_http_client('http://catalogue', authenticate)
        resp = catalogue.get('/tags')
        resp.assert_status_code(200)
        tags = jsonpath('$.tags[*]', resp)

        # GET http://catalogue/catalogue (endp 29)
        qstr = '?' + urlencode([('page', '1'), ('size', size), ('sort', 'id'), ('tags', tags)])
        resp = catalogue.get('/catalogue' + qstr)
        resp.assert_status_code(200)
        id_ = jsonpath('$[*].id', resp)

        # GET http://catalogue/catalogue/{id} (endp 27)
        resp = catalogue.get(f'/catalogue/{id_}')
        resp.assert_status_code(200)

    @json_dataset('data/dataset_28.json')
    @clear_session({'spanId': 28})
    def test_28_get_catalogue_size(self, data_row):
        tags, = data_row

        # GET http://catalogue/catalogue/size (endp 28)
        catalogue = get_http_client('http://catalogue', authenticate)
        qstr = '?' + urlencode([('tags', tags)])
        resp = catalogue.get('/catalogue/size' + qstr)
        resp.assert_status_code(200)


@data_driven_tests
class Tests_front_end(unittest.TestCase):

    @clear_session({'spanId': 13})
    def test_13_get_catalogue_id(self):
        # GET http://front-end/orders (endp 19)
        front_end = get_http_client('http://front-end', authenticate)
        resp = front_end.get('/orders', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(201)
        resp.assert_jsonpath('$[*].address.city', expected_value='Glasgow')
        id_ = jsonpath('$[*].items[*].itemId', resp)

        # GET http://front-end/catalogue/{id} (endp 13)
        resp = front_end.get(f'/catalogue/{id_}', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 20})
    def test_20_get_orders_href(self):
        # GET http://front-end/orders (endp 19)
        front_end = get_http_client('http://front-end', authenticate)
        resp = front_end.get('/orders', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(201)
        resp.assert_jsonpath('$[*].address.city', expected_value='Glasgow')
        href = url_part('/2', jsonpath('$[*]._links.self.href', resp))

        # GET http://front-end/orders/{href} (endp 20)
        resp = front_end.get(f'/orders/{href}', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.address.city', expected_value='Glasgow')


@data_driven_tests
class Tests_orders(unittest.TestCase):

    @json_dataset('data/dataset_36.json')
    @clear_session({'spanId': 36})
    def test_36_get_orders_href(self, data_row):
        href, = data_row

        # GET http://orders/orders/{href} (endp 36)
        orders = get_http_client('http://orders', authenticate)
        resp = orders.get(f'/orders/{href}')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.address.city', expected_value='Glasgow')


@data_driven_tests
class Tests_payment(unittest.TestCase):

    @json_dataset('data/dataset_38.json')
    @clear_session({'spanId': 38})
    def test_38_post_paymentAuth(self, data_row):
        ccv, country, expires, id_, id1, id2, longNum, number, postcode, street = data_row

        # POST http://payment/paymentAuth (endp 38)
        payment = get_http_client('http://payment', authenticate)
        with open('data/payload_for_endp_38.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address.country', country)
        apply_into_json(json_payload, '$.address.id', id_)
        apply_into_json(json_payload, '$.address.number', number)
        apply_into_json(json_payload, '$.address.postcode', postcode)
        apply_into_json(json_payload, '$.address.street', street)
        apply_into_json(json_payload, '$.amount', float(random.uniform(12.98, 122.979996)))
        apply_into_json(json_payload, '$.card.ccv', ccv)
        apply_into_json(json_payload, '$.card.expires', expires)
        apply_into_json(json_payload, '$.card.id', id1)
        apply_into_json(json_payload, '$.card.longNum', longNum)
        apply_into_json(json_payload, '$.customer.id', id2)
        resp = payment.post('/paymentAuth', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(200)


@data_driven_tests
class Tests_shipping(unittest.TestCase):

    @json_dataset('data/dataset_39.json')
    @clear_session({'spanId': 39})
    def test_39_post_shipping(self, data_row):
        name, = data_row

        # POST http://shipping/shipping (endp 39)
        shipping = get_http_client('http://shipping', authenticate)
        with open('data/payload_for_endp_39.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.id', str(uuid.uuid4()))
        apply_into_json(json_payload, '$.name', name)
        resp = shipping.post('/shipping', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)


@data_driven_tests
class Tests_user(unittest.TestCase):

    @clear_session({'spanId': 31})
    def test_31_get_customers_customerId(self):
        # GET http://user/login (endp 32)
        user = get_http_client('http://user', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customerId = jsonpath('$.user.id', resp)

        # GET http://user/customers/{customerId} (endp 31)
        resp = user.get(f'/customers/{customerId}')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.lastName', expected_value='Name')

    @clear_session({'spanId': 33})
    def test_33_get_customers_customerId_addresses(self):
        # GET http://user/login (endp 32)
        user = get_http_client('http://user', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customerId = jsonpath('$.user.id', resp)

        # GET http://user/customers/{customerId}/addresses (endp 33)
        resp = user.get(f'/customers/{customerId}/addresses')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$._embedded.address[*].city', expected_value='Glasgow')

    @clear_session({'spanId': 34})
    def test_34_get_customers_customerId_cards(self):
        # GET http://user/login (endp 32)
        user = get_http_client('http://user', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customerId = jsonpath('$.user.id', resp)

        # GET http://user/customers/{customerId}/cards (endp 34)
        resp = user.get(f'/customers/{customerId}/cards')
        resp.assert_status_code(200)
