from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_carts(unittest.TestCase):

    @clear_session({'spanId': 22})
    def test_22_delete_carts_customerId(self):
        # GET http://user/login (endp 32)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customer = jsonpath('$.user._links.customer.href', resp)

        # POST http://orders/orders (endp 35)
        address = 'http://user/addresses/57a98d98e4b00679b4a830b0'
        card = 'http://user/cards/57a98d98e4b00679b4a830b1'
        items = 'http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items'
        orders = get_http_target('TARGET_ORDERS', authenticate)
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
        carts = get_http_target('TARGET_CARTS', authenticate)
        resp = carts.delete(f'/carts/{customerId}')
        resp.assert_status_code(202)

    @clear_session({'spanId': 23})
    def test_23_post_carts_customerId_items(self):
        # GET http://user/login (endp 32)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customer = jsonpath('$.user._links.customer.href', resp)

        # POST http://orders/orders (endp 35)
        address = 'http://user/addresses/57a98d98e4b00679b4a830b0'
        card = 'http://user/cards/57a98d98e4b00679b4a830b1'
        items = 'http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items'
        orders = get_http_target('TARGET_ORDERS', authenticate)
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
        carts = get_http_target('TARGET_CARTS', authenticate)
        with open('data/payload_for_endp_23.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.itemId', itemId)
        apply_into_json(json_payload, '$.unitPrice', unitPrice)
        resp = carts.post(f'/carts/{customerId}/items', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)

    @clear_session({'spanId': 25})
    def test_25_patch_carts_customerId_items(self):
        # GET http://user/login (endp 32)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customer = jsonpath('$.user._links.customer.href', resp)

        # POST http://orders/orders (endp 35)
        address = 'http://user/addresses/57a98d98e4b00679b4a830b0'
        card = 'http://user/cards/57a98d98e4b00679b4a830b1'
        items = 'http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items'
        orders = get_http_target('TARGET_ORDERS', authenticate)
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
        carts = get_http_target('TARGET_CARTS', authenticate)
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

    @clear_session({'spanId': 24})
    def test_24_get_carts_customerId_merge(self):
        # GET http://user/login (endp 32)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customer = jsonpath('$.user._links.customer.href', resp)

        # POST http://orders/orders (endp 35)
        address = 'http://user/addresses/57a98d98e4b00679b4a830b0'
        card = 'http://user/cards/57a98d98e4b00679b4a830b1'
        items = 'http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items'
        orders = get_http_target('TARGET_ORDERS', authenticate)
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
        sessionId = '1J9WwRDQsVd4ymiharhdwevNEWEUP2x2'
        carts = get_http_target('TARGET_CARTS', authenticate)
        qstr = '?' + urlencode([('sessionId', sessionId)])
        resp = carts.get(f'/carts/{customerId}/merge' + qstr)
        resp.assert_status_code(202)


@data_driven_tests
class Tests_catalogue(unittest.TestCase):

    @clear_session({'spanId': 27})
    def test_27_get_catalogue_id(self):
        # GET http://catalogue/tags (endp 30)
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        resp = catalogue.get('/tags')
        resp.assert_status_code(200)
        tags = jsonpath('$.tags[*]', resp)

        # GET http://catalogue/catalogue (endp 29)
        size = '6'
        qstr = '?' + urlencode([('page', '1'), ('size', size), ('sort', 'id'), ('tags', tags)])
        resp = catalogue.get('/catalogue' + qstr)
        resp.assert_status_code(200)
        id_ = jsonpath('$[*].id', resp)

        # GET http://catalogue/catalogue/{id} (endp 27)
        resp = catalogue.get(f'/catalogue/{id_}')
        resp.assert_status_code(200)

    @clear_session({'spanId': 28})
    def test_28_get_catalogue_size(self):
        # GET http://catalogue/catalogue/size (endp 28)
        tags = ''
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        qstr = '?' + urlencode([('tags', tags)])
        resp = catalogue.get('/catalogue/size' + qstr)
        resp.assert_status_code(200)


@data_driven_tests
class Tests_front_end(unittest.TestCase):

    @clear_session({'spanId': 1})
    def test_01_get_(self):
        # GET http://front-end/ (endp 1)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    @clear_session({'spanId': 12})
    def test_12_get_cart(self):
        # GET http://front-end/tags (endp 21)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
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

    @clear_session({'spanId': 15})
    def test_15_get_catalogue(self):
        # GET http://front-end/tags (endp 21)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/tags', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        tags = jsonpath('$.tags[*]', resp)

        # GET http://front-end/catalogue (endp 15)
        size = '6'
        qstr = '?' + urlencode([('page', '1'), ('size', size), ('tags', tags)])
        resp = front_end.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 6})
    def test_06_get_category_html(self):
        # GET http://front-end/category.html (endp 6)
        size = '5'
        tags = 'geek,blue'
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        qstr = '?' + urlencode([('page', '1'), ('size', size), ('tags', tags)])
        resp = front_end.get('/category.html' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title', expected_value='Filters ')

    @clear_session({'spanId': 16})
    def test_16_get_customer_order_html(self):
        # GET http://front-end/customer-order.html (endp 16)
        order = '/orders/6053f8feeae7630007e44bcf'
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        qstr = '?' + urlencode([('order', order)])
        resp = front_end.get('/customer-order.html' + qstr)
        resp.assert_status_code(200)
        resp.assert_cssselect('div#customer-order div.box h2', expected_value='Order #')

    @clear_session({'spanId': 18})
    def test_18_get_customers_customerId(self):
        # GET http://front-end/login (endp 8)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
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
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/login', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
        resp.assert_cssselect('p', expected_value='Cookie is set')
        customerId = get_data_from_cookie('logged_in')

        # GET http://front-end/customers/{customerId} (endp 44)
        resp = front_end.get(f'/customers/{customerId}', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 7})
    def test_07_get_detail_html(self):
        # GET http://front-end/catalogue (endp 5)
        size = '5'
        tags = ''
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
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

    @clear_session({'spanId': 20})
    def test_20_get_orders_href(self):
        # GET http://front-end/catalogue (endp 5)
        size = '5'
        tags = ''
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
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

    @clear_session({'spanId': 36})
    def test_36_get_orders_href(self):
        # GET http://user/login (endp 32)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customer = jsonpath('$.user._links.customer.href', resp)

        # POST http://orders/orders (endp 35)
        address = 'http://user/addresses/57a98d98e4b00679b4a830b0'
        card = 'http://user/cards/57a98d98e4b00679b4a830b1'
        items = 'http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items'
        orders = get_http_target('TARGET_ORDERS', authenticate)
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

    @clear_session({'spanId': 38})
    def test_38_post_paymentAuth(self):
        # POST http://payment/paymentAuth (endp 38)
        ccv = '958'
        country = 'United Kingdom'
        expires = '08/19'
        id_ = None
        id1 = None
        id2 = None
        longNum = '5544154011345918'
        number = '246'
        postcode = 'G67 3DL'
        street = 'Whitelees Road'
        payment = get_http_target('TARGET_PAYMENT', authenticate)
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

    @clear_session({'spanId': 39})
    def test_39_post_shipping(self):
        # POST http://shipping/shipping (endp 39)
        name = '57a98d98e4b00679b4a830b2'
        shipping = get_http_target('TARGET_SHIPPING', authenticate)
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
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customer = jsonpath('$.user._links.customer.href', resp)

        # POST http://orders/orders (endp 35)
        address = 'http://user/addresses/57a98d98e4b00679b4a830b0'
        card = 'http://user/cards/57a98d98e4b00679b4a830b1'
        items = 'http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items'
        orders = get_http_target('TARGET_ORDERS', authenticate)
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

    @clear_session({'spanId': 33})
    def test_33_get_customers_customerId_addresses(self):
        # GET http://user/login (endp 32)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customer = jsonpath('$.user._links.customer.href', resp)

        # POST http://orders/orders (endp 35)
        address = 'http://user/addresses/57a98d98e4b00679b4a830b0'
        card = 'http://user/cards/57a98d98e4b00679b4a830b1'
        items = 'http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items'
        orders = get_http_target('TARGET_ORDERS', authenticate)
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

    @clear_session({'spanId': 34})
    def test_34_get_customers_customerId_cards(self):
        # GET http://user/login (endp 32)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get('/login')
        resp.assert_status_code(200)
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
        customer = jsonpath('$.user._links.customer.href', resp)

        # POST http://orders/orders (endp 35)
        address = 'http://user/addresses/57a98d98e4b00679b4a830b0'
        card = 'http://user/cards/57a98d98e4b00679b4a830b1'
        items = 'http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items'
        orders = get_http_target('TARGET_ORDERS', authenticate)
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
