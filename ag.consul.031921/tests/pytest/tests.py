from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_carts(unittest.TestCase):

    @json_dataset('data/dataset_22.json')
    @clear_session({'spanId': 22})
    def test_22_delete_carts_customerId(self, data_row):
        address, card, items = data_row

        # GET http://user/login (endp 32)
        user = get_http_client('http://user', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customer = jsonpath('$.user._links.customer.href', resp)

        # POST http://orders/orders (endp 35)
        orders = get_http_client('http://orders', authenticate)
        with open('data/payload_for_endp_35.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address', address)
        apply_into_json(json_payload, '$.card', card)
        apply_into_json(json_payload, '$.customer', customer)
        apply_into_json(json_payload, '$.items', items)
        resp = orders.post('/orders', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)
        resp.assert_jsonpath('$.address.city', expected_value='Glasgow')
        customerId = jsonpath('$.customerId', resp)

        # DELETE http://carts/carts/{customerId} (endp 22)
        carts = get_http_client('http://carts', authenticate)
        resp = carts.delete(f'/carts/{customerId}')
        resp.assert_status_code(202)

    @json_dataset('data/dataset_23.json')
    @clear_session({'spanId': 23})
    def test_23_post_carts_customerId_items(self, data_row):
        address, card, items = data_row

        # GET http://user/login (endp 32)
        user = get_http_client('http://user', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customer = jsonpath('$.user._links.customer.href', resp)

        # POST http://orders/orders (endp 35)
        orders = get_http_client('http://orders', authenticate)
        with open('data/payload_for_endp_35.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address', address)
        apply_into_json(json_payload, '$.card', card)
        apply_into_json(json_payload, '$.customer', customer)
        apply_into_json(json_payload, '$.items', items)
        resp = orders.post('/orders', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)
        resp.assert_jsonpath('$.address.city', expected_value='Glasgow')
        itemId = jsonpath('$.items[*].itemId', resp)
        unitPrice = jsonpath('$.items[*].unitPrice', resp)
        customerId = jsonpath('$.customerId', resp)

        # POST http://carts/carts/{customerId}/items (endp 23)
        carts = get_http_client('http://carts', authenticate)
        with open('data/payload_for_endp_23.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.itemId', itemId)
        apply_into_json(json_payload, '$.unitPrice', unitPrice)
        resp = carts.post(f'/carts/{customerId}/items', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)

    @json_dataset('data/dataset_25.json')
    @clear_session({'spanId': 25})
    def test_25_patch_carts_customerId_items(self, data_row):
        address, card, items = data_row

        # GET http://user/login (endp 32)
        user = get_http_client('http://user', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customer = jsonpath('$.user._links.customer.href', resp)

        # POST http://orders/orders (endp 35)
        orders = get_http_client('http://orders', authenticate)
        with open('data/payload_for_endp_35.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address', address)
        apply_into_json(json_payload, '$.card', card)
        apply_into_json(json_payload, '$.customer', customer)
        apply_into_json(json_payload, '$.items', items)
        resp = orders.post('/orders', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)
        resp.assert_jsonpath('$.address.city', expected_value='Glasgow')
        quantity = jsonpath('$.items[*].quantity', resp)
        customerId = jsonpath('$.customerId', resp)

        # GET http://carts/carts/{customerId}/items (endp 26)
        carts = get_http_client('http://carts', authenticate)
        resp = carts.get(f'/carts/{customerId}/items', headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(200)
        itemId = jsonpath('$[*].itemId', resp)
        unitPrice = jsonpath('$[*].unitPrice', resp)

        # PATCH http://carts/carts/{customerId}/items (endp 25)
        with open('data/payload_for_endp_25.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.itemId', itemId)
        apply_into_json(json_payload, '$.quantity', quantity)
        apply_into_json(json_payload, '$.unitPrice', unitPrice)
        resp = carts.patch(f'/carts/{customerId}/items', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(202)

    @json_dataset('data/dataset_24.json')
    @clear_session({'spanId': 24})
    def test_24_get_carts_customerId_merge(self, data_row):
        address, card, items, sessionId = data_row

        # GET http://user/login (endp 32)
        user = get_http_client('http://user', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customer = jsonpath('$.user._links.customer.href', resp)

        # POST http://orders/orders (endp 35)
        orders = get_http_client('http://orders', authenticate)
        with open('data/payload_for_endp_35.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address', address)
        apply_into_json(json_payload, '$.card', card)
        apply_into_json(json_payload, '$.customer', customer)
        apply_into_json(json_payload, '$.items', items)
        resp = orders.post('/orders', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)
        resp.assert_jsonpath('$.address.city', expected_value='Glasgow')
        customerId = jsonpath('$.customerId', resp)

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

    @clear_session({'spanId': 1})
    def test_01_get_(self):
        # GET http://front-end/ (endp 1)
        front_end = get_http_client('http://front-end', authenticate)
        resp = front_end.get('/')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    @clear_session({'spanId': 12})
    def test_12_get_cart(self):
        # GET http://front-end/tags (endp 21)
        front_end = get_http_client('http://front-end', authenticate)
        resp = front_end.get('/tags', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        tags = jsonpath('$.tags[*]', resp)

        # GET http://front-end/catalogue/size (endp 14)
        qstr = '?' + urlencode([('tags', tags)])
        resp = front_end.get('/catalogue/size' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.name', expected_value='Holy')

        # GET http://front-end/cart (endp 12)
        resp = front_end.get('/cart', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @json_dataset('data/dataset_15.json')
    @clear_session({'spanId': 15})
    def test_15_get_catalogue(self, data_row):
        size, = data_row

        # GET http://front-end/tags (endp 21)
        front_end = get_http_client('http://front-end', authenticate)
        resp = front_end.get('/tags', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        tags = jsonpath('$.tags[*]', resp)

        # GET http://front-end/catalogue (endp 15)
        qstr = '?' + urlencode([('page', '1'), ('size', size), ('tags', tags)])
        resp = front_end.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @json_dataset('data/dataset_6.json')
    @clear_session({'spanId': 6})
    def test_06_get_category_html(self, data_row):
        size, tags = data_row

        # GET http://front-end/category.html (endp 6)
        front_end = get_http_client('http://front-end', authenticate)
        qstr = '?' + urlencode([('page', '1'), ('size', size), ('tags', tags)])
        resp = front_end.get('/category.html' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title', expected_value='Filters ')

    @json_dataset('data/dataset_16.json')
    @clear_session({'spanId': 16})
    def test_16_get_customer_order_html(self, data_row):
        order, = data_row

        # GET http://front-end/customer-order.html (endp 16)
        front_end = get_http_client('http://front-end', authenticate)
        qstr = '?' + urlencode([('order', order)])
        resp = front_end.get('/customer-order.html' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('div#customer-order div.box h2', expected_value='Order #')

    @clear_session({'spanId': 18})
    def test_18_get_customers_customerId(self):
        # GET http://front-end/login (endp 8)
        front_end = get_http_client('http://front-end', authenticate)
        resp = front_end.get('/login', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        resp.assert_cssselect('p', expected_value='Cookie is set')
        customerId = get_data_from_cookie('logged_in')

        # GET http://front-end/customers/{customerId} (endp 18)
        resp = front_end.get(f'/customers/{customerId}', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.lastName', expected_value='Name')

    @clear_session({'spanId': 44})
    def test_44_get_customers_customerId(self):
        # GET http://front-end/login (endp 8)
        front_end = get_http_client('http://front-end', authenticate)
        resp = front_end.get('/login', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        resp.assert_cssselect('p', expected_value='Cookie is set')
        customerId = get_data_from_cookie('logged_in')

        # GET http://front-end/customers/{customerId} (endp 44)
        resp = front_end.get(f'/customers/{customerId}', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @json_dataset('data/dataset_7.json')
    @clear_session({'spanId': 7})
    def test_07_get_detail_html(self, data_row):
        size, tags = data_row

        # GET http://front-end/catalogue (endp 5)
        front_end = get_http_client('http://front-end', authenticate)
        qstr = '?' + urlencode([('page', '1'), ('size', size), ('sort', 'id'), ('tags', tags)])
        resp = front_end.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        id_ = jsonpath('$[*].id', resp)

        # POST http://front-end/cart (endp 4)
        with open('data/payload_for_endp_4.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.id', id_)
        resp = front_end.post('/cart', json=json_payload)
        resp.assert_status_code(201)

        # GET http://front-end/basket.html (endp 2)
        resp = front_end.get('/basket.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#basket div.box form h1', expected_value='Shopping cart')

        # DELETE http://front-end/cart (endp 3)
        resp = front_end.delete('/cart')
        resp.assert_status_code(202)

        # POST http://front-end/orders (endp 9)
        resp = front_end.post('/orders', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(201)
        resp.assert_jsonpath('$.address.city', expected_value='Glasgow')
        id1 = jsonpath('$.items[*].itemId', resp)

        # GET http://front-end/detail.html (endp 7)
        qstr = '?' + urlencode([('id', id1)])
        resp = front_end.get('/detail.html' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('div#content div.container div div.row.same-height-row div div.box.same-height h3', expected_value='You may also like these products')

    @json_dataset('data/dataset_20.json')
    @clear_session({'spanId': 20})
    def test_20_get_orders_href(self, data_row):
        size, tags = data_row

        # GET http://front-end/catalogue (endp 5)
        front_end = get_http_client('http://front-end', authenticate)
        qstr = '?' + urlencode([('page', '1'), ('size', size), ('sort', 'id'), ('tags', tags)])
        resp = front_end.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        id_ = jsonpath('$[*].id', resp)

        # GET http://front-end/catalogue/{id} (endp 13)
        resp = front_end.get(f'/catalogue/{id_}', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

        # GET http://front-end/address (endp 10)
        resp = front_end.get('/address', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.city', expected_value='Glasgow')

        # GET http://front-end/card (endp 11)
        resp = front_end.get('/card', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

        # GET http://front-end/customer-orders.html (endp 17)
        resp = front_end.get('/customer-orders.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#customer-orders div.box h1', expected_value='My orders')

        # GET http://front-end/orders (endp 19)
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
        address, card, items = data_row

        # GET http://user/login (endp 32)
        user = get_http_client('http://user', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customer = jsonpath('$.user._links.customer.href', resp)

        # POST http://orders/orders (endp 35)
        orders = get_http_client('http://orders', authenticate)
        with open('data/payload_for_endp_35.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address', address)
        apply_into_json(json_payload, '$.card', card)
        apply_into_json(json_payload, '$.customer', customer)
        apply_into_json(json_payload, '$.items', items)
        resp = orders.post('/orders', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)
        resp.assert_jsonpath('$.address.city', expected_value='Glasgow')
        custId = jsonpath('$.customerId', resp)

        # GET http://orders/orders/search/customerId (endp 37)
        qstr = '?' + urlencode([('custId', custId), ('sort', 'date')])
        resp = orders.get('/orders/search/customerId' + qstr)
        resp.assert_status_code(200)
        resp.assert_jsonpath('$._embedded.customerOrders[*].address.city', expected_value='Glasgow')
        href = url_part('/2', jsonpath('$._embedded.customerOrders[*]._links.order.href', resp))

        # GET http://orders/orders/{href} (endp 36)
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

    @json_dataset('data/dataset_31.json')
    @clear_session({'spanId': 31})
    def test_31_get_customers_customerId(self, data_row):
        address, card, items = data_row

        # GET http://user/login (endp 32)
        user = get_http_client('http://user', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customer = jsonpath('$.user._links.customer.href', resp)

        # POST http://orders/orders (endp 35)
        orders = get_http_client('http://orders', authenticate)
        with open('data/payload_for_endp_35.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address', address)
        apply_into_json(json_payload, '$.card', card)
        apply_into_json(json_payload, '$.customer', customer)
        apply_into_json(json_payload, '$.items', items)
        resp = orders.post('/orders', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)
        resp.assert_jsonpath('$.address.city', expected_value='Glasgow')
        customerId = jsonpath('$.customerId', resp)

        # GET http://user/customers/{customerId} (endp 31)
        resp = user.get(f'/customers/{customerId}')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.lastName', expected_value='Name')

    @json_dataset('data/dataset_33.json')
    @clear_session({'spanId': 33})
    def test_33_get_customers_customerId_addresses(self, data_row):
        address, card, items = data_row

        # GET http://user/login (endp 32)
        user = get_http_client('http://user', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customer = jsonpath('$.user._links.customer.href', resp)

        # POST http://orders/orders (endp 35)
        orders = get_http_client('http://orders', authenticate)
        with open('data/payload_for_endp_35.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address', address)
        apply_into_json(json_payload, '$.card', card)
        apply_into_json(json_payload, '$.customer', customer)
        apply_into_json(json_payload, '$.items', items)
        resp = orders.post('/orders', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)
        resp.assert_jsonpath('$.address.city', expected_value='Glasgow')
        customerId = jsonpath('$.customerId', resp)

        # GET http://user/customers/{customerId}/addresses (endp 33)
        resp = user.get(f'/customers/{customerId}/addresses')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$._embedded.address[*].city', expected_value='Glasgow')

    @json_dataset('data/dataset_34.json')
    @clear_session({'spanId': 34})
    def test_34_get_customers_customerId_cards(self, data_row):
        address, card, items = data_row

        # GET http://user/login (endp 32)
        user = get_http_client('http://user', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customer = jsonpath('$.user._links.customer.href', resp)

        # POST http://orders/orders (endp 35)
        orders = get_http_client('http://orders', authenticate)
        with open('data/payload_for_endp_35.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address', address)
        apply_into_json(json_payload, '$.card', card)
        apply_into_json(json_payload, '$.customer', customer)
        apply_into_json(json_payload, '$.items', items)
        resp = orders.post('/orders', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)
        resp.assert_jsonpath('$.address.city', expected_value='Glasgow')
        customerId = jsonpath('$.customerId', resp)

        # GET http://user/customers/{customerId}/cards (endp 34)
        resp = user.get(f'/customers/{customerId}/cards')
        resp.assert_status_code(200)
