from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_carts(unittest.TestCase):

    @clear_session({'spanId': 46})
    def test_46_delete_carts_customerId(self):
        # POST http://orders/orders (endp 57)
        address = 'http://user/addresses/57a98d98e4b00679b4a830b0'
        card = 'http://user/cards/57a98d98e4b00679b4a830b1'
        customer = 'http://user/customers/57a98d98e4b00679b4a830b2'
        items = 'http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items'
        orders = get_http_target('TARGET_ORDERS', authenticate)
        with open('data/payload_for_endp_57.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address', address)
        apply_into_json(json_payload, '$.card', card)
        apply_into_json(json_payload, '$.customer', customer)
        apply_into_json(json_payload, '$.items', items)
        resp = orders.post('/orders', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)
        resp.assert_jsonpath('$.address.city', expected_value='Glasgow')
        customerId = jsonpath('$.customerId', resp)

        # DELETE http://carts/carts/{customerId} (endp 46)
        carts = get_http_target('TARGET_CARTS', authenticate)
        resp = carts.delete(f'/carts/{customerId}')
        resp.assert_status_code(202)

    @clear_session({'spanId': 13})
    def test_13_get_carts_customerId_items(self):
        # POST http://orders/orders (endp 57)
        address = 'http://user/addresses/57a98d98e4b00679b4a830b0'
        card = 'http://user/cards/57a98d98e4b00679b4a830b1'
        customer = 'http://user/customers/57a98d98e4b00679b4a830b2'
        items = 'http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items'
        orders = get_http_target('TARGET_ORDERS', authenticate)
        with open('data/payload_for_endp_57.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address', address)
        apply_into_json(json_payload, '$.card', card)
        apply_into_json(json_payload, '$.customer', customer)
        apply_into_json(json_payload, '$.items', items)
        resp = orders.post('/orders', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)
        resp.assert_jsonpath('$.address.city', expected_value='Glasgow')
        customerId = jsonpath('$.customerId', resp)

        # GET http://carts/carts/{customerId}/items (endp 13)
        carts = get_http_target('TARGET_CARTS', authenticate)
        resp = carts.get(f'/carts/{customerId}/items', headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 56})
    def test_56_post_carts_customerId_items(self):
        # GET http://catalogue/catalogue (endp 10)
        size = '5'
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        qstr = '?' + urlencode([('page', '1'), ('size', size), ('tags', '')])
        resp = catalogue.get('/catalogue' + qstr)
        resp.assert_regex_in_body(r'.*Holy.*')
        itemId = jsonpath('$[*].id', resp)
        unitPrice = jsonpath('$[*].price', resp)

        # POST http://orders/orders (endp 57)
        address = 'http://user/addresses/57a98d98e4b00679b4a830b0'
        card = 'http://user/cards/57a98d98e4b00679b4a830b1'
        customer = 'http://user/customers/57a98d98e4b00679b4a830b2'
        items = 'http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items'
        orders = get_http_target('TARGET_ORDERS', authenticate)
        with open('data/payload_for_endp_57.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address', address)
        apply_into_json(json_payload, '$.card', card)
        apply_into_json(json_payload, '$.customer', customer)
        apply_into_json(json_payload, '$.items', items)
        resp = orders.post('/orders', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)
        resp.assert_jsonpath('$.address.city', expected_value='Glasgow')
        customerId = jsonpath('$.customerId', resp)

        # POST http://carts/carts/{customerId}/items (endp 56)
        carts = get_http_target('TARGET_CARTS', authenticate)
        with open('data/payload_for_endp_56.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.itemId', itemId)
        apply_into_json(json_payload, '$.unitPrice', unitPrice)
        resp = carts.post(f'/carts/{customerId}/items', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)

    @clear_session({'spanId': 12})
    def test_12_get_carts_customerId_merge(self):
        # POST http://orders/orders (endp 57)
        address = 'http://user/addresses/57a98d98e4b00679b4a830b0'
        card = 'http://user/cards/57a98d98e4b00679b4a830b1'
        customer = 'http://user/customers/57a98d98e4b00679b4a830b2'
        items = 'http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items'
        orders = get_http_target('TARGET_ORDERS', authenticate)
        with open('data/payload_for_endp_57.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address', address)
        apply_into_json(json_payload, '$.card', card)
        apply_into_json(json_payload, '$.customer', customer)
        apply_into_json(json_payload, '$.items', items)
        resp = orders.post('/orders', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)
        resp.assert_jsonpath('$.address.city', expected_value='Glasgow')
        customerId = jsonpath('$.customerId', resp)

        # GET http://carts/carts/{customerId}/merge (endp 12)
        sessionId = 'XQmVKODtqAcXS6ZfF4HlZubZBEgShjds'
        carts = get_http_target('TARGET_CARTS', authenticate)
        qstr = '?' + urlencode([('sessionId', sessionId)])
        resp = carts.get(f'/carts/{customerId}/merge' + qstr)
        resp.assert_status_code(202)


@data_driven_tests
class Tests_catalogue(unittest.TestCase):

    @clear_session({'spanId': 54})
    def test_54_get_catalogue_id(self):
        # GET http://catalogue/catalogue (endp 10)
        size = '5'
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        qstr = '?' + urlencode([('page', '1'), ('size', size), ('tags', '')])
        resp = catalogue.get('/catalogue' + qstr)
        resp.assert_regex_in_body(r'.*Holy.*')
        id_ = jsonpath('$[*].id', resp)

        # GET http://catalogue/catalogue/{id} (endp 54)
        resp = catalogue.get(f'/catalogue/{id_}')
        resp.assert_status_code(200)

    @clear_session({'spanId': 9})
    def test_09_get_catalogue_size(self):
        # GET http://catalogue/catalogue/size (endp 9)
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        qstr = '?' + urlencode([('tags', '')])
        resp = catalogue.get('/catalogue/size' + qstr)
        resp.assert_status_code(200)

    @clear_session({'spanId': 11})
    def test_11_get_tags(self):
        # GET http://catalogue/tags (endp 11)
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        resp = catalogue.get('/tags')
        resp.assert_status_code(200)


@data_driven_tests
class Tests_front_end(unittest.TestCase):

    @clear_session({'spanId': 1})
    def test_01_get_(self):
        # GET http://front-end/ (endp 1)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/')
        resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    @clear_session({'spanId': 26})
    def test_26_get_param(self):
        # GET http://front-end/{param} (endp 26)
        param = 'navbar.html'
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get(f'/{param}', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 59})
    def test_59_get_basket_html(self):
        # GET http://front-end/basket.html (endp 59)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/basket.html')
        resp.assert_status_code(200)
        resp.assert_cssselect('div#basket div.box form h1', expected_value='Shopping cart')

    @clear_session({'spanId': 2})
    def test_02_get_cart(self):
        # GET http://front-end/cart (endp 2)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/cart', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 43})
    def test_43_delete_cart(self):
        # DELETE http://front-end/cart (endp 43)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.delete('/cart')
        resp.assert_status_code(202)

    @clear_session({'spanId': 61})
    def test_61_post_cart(self):
        # POST http://front-end/orders (endp 66)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.post('/orders')
        resp.assert_status_code(201)
        resp.assert_jsonpath('$.address.city', expected_value='Glasgow')
        id_ = jsonpath('$.items[*].itemId', resp)

        # POST http://front-end/cart (endp 61)
        with open('data/payload_for_endp_61.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.id', id_)
        resp = front_end.post('/cart', json=json_payload)
        resp.assert_status_code(201)

    @clear_session({'spanId': 37})
    def test_37_get_catalogue(self):
        # GET http://front-end/catalogue (endp 37)
        size = '6'
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        qstr = '?' + urlencode([('page', '1'), ('size', size), ('tags', '')])
        resp = front_end.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 52})
    def test_52_get_catalogue_id(self):
        # GET http://front-end/catalogue (endp 4)
        size = '5'
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        qstr = '?' + urlencode([('page', '1'), ('size', size), ('tags', '')])
        resp = front_end.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_regex_in_body(r'.*Holy.*')
        id_ = jsonpath('$[*].id', resp)

        # GET http://front-end/catalogue/{id} (endp 52)
        resp = front_end.get(f'/catalogue/{id_}', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 3})
    def test_03_get_catalogue_size(self):
        # GET http://front-end/catalogue/size (endp 3)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        qstr = '?' + urlencode([('tags', '')])
        resp = front_end.get('/catalogue/size' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 36})
    def test_36_get_catalogue_size(self):
        # GET http://front-end/catalogue/size (endp 36)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        qstr = '?' + urlencode([('tags', '')])
        resp = front_end.get('/catalogue/size' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 34})
    def test_34_get_category_html(self):
        # GET http://front-end/category.html (endp 34)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/category.html')
        resp.assert_cssselect('div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title', expected_value='Filters ')

    @clear_session({'spanId': 5})
    def test_05_get_customers_customerId(self):
        # GET http://front-end/customers/{customerId} (endp 5)
        customerId = '7w4TGI0pnIxn5TEFoHX6O2spPdXa3dut'
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get(f'/customers/{customerId}', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_jsonpath('$.lastName', expected_value='Name')

    @clear_session({'spanId': 30})
    def test_30_get_customers_customerId(self):
        # GET http://front-end/customers/{customerId} (endp 30)
        customerId = '7w4TGI0pnIxn5TEFoHX6O2spPdXa3dut'
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get(f'/customers/{customerId}', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 45})
    def test_45_get_detail_html(self):
        # POST http://front-end/orders (endp 66)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.post('/orders')
        resp.assert_status_code(201)
        resp.assert_jsonpath('$.address.city', expected_value='Glasgow')
        id_ = jsonpath('$.items[*].itemId', resp)

        # GET http://front-end/detail.html (endp 45)
        qstr = '?' + urlencode([('id', id_)])
        resp = front_end.get('/detail.html' + qstr)
        resp.assert_cssselect('div#content div.container div div.row.same-height-row div div.box.same-height h3', expected_value='You may also like these products')

    @clear_session({'spanId': 23})
    def test_23_get_footer_html(self):
        # GET http://front-end/footer.html (endp 23)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/footer.html', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_cssselect('div#copyright div.container div p.pull-left a', expected_value='Weaveworks')

    @clear_session({'spanId': 6})
    def test_06_get_index_html(self):
        # GET http://front-end/index.html (endp 6)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/index.html')
        resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    # authentication-related test
    @clear_session({'spanId': 25})
    def test_25_get_login(self):
        # GET http://front-end/login (endp 25)
        front_end = get_http_target('TARGET_FRONT_END', dummy_auth)
        resp = front_end.get('/login', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_cssselect('p', expected_value='Cookie is set')

    @clear_session({'spanId': 7})
    def test_07_get_orders(self):
        # GET http://front-end/orders (endp 7)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/orders', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(201)

    @clear_session({'spanId': 8})
    def test_08_get_tags(self):
        # GET http://front-end/tags (endp 8)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/tags', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 38})
    def test_38_get_tags(self):
        # GET http://front-end/tags (endp 38)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/tags', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)

    @clear_session({'spanId': 27})
    def test_27_get_topbar_html(self):
        # GET http://front-end/topbar.html (endp 27)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/topbar.html', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_cssselect('div#top div.container div.offer a.btn.btn-success.btn-sm', expected_value='Offer of the day')


@data_driven_tests
class Tests_mockintosh(unittest.TestCase):

    @clear_session({'spanId': 39})
    def test_39_get_(self):
        # GET http://mockintosh/ (endp 39)
        mockintosh = get_http_target('TARGET_MOCKINTOSH', authenticate)
        resp = mockintosh.get('/')
        resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    @clear_session({'spanId': 47})
    def test_47_get_catalogue(self):
        # GET http://mockintosh/catalogue (endp 47)
        size = '6'
        mockintosh = get_http_target('TARGET_MOCKINTOSH', authenticate)
        qstr = '?' + urlencode([('page', '1'), ('size', size), ('tags', '')])
        resp = mockintosh.get('/catalogue' + qstr)
        resp.assert_status_code(200)

    @clear_session({'spanId': 48})
    def test_48_get_catalogue_size(self):
        # GET http://mockintosh/catalogue/size (endp 48)
        mockintosh = get_http_target('TARGET_MOCKINTOSH', authenticate)
        qstr = '?' + urlencode([('tags', '')])
        resp = mockintosh.get('/catalogue/size' + qstr)
        resp.assert_status_code(200)

    @clear_session({'spanId': 17})
    def test_17_get_customers_id(self):
        # GET http://mockintosh/customers/undefined (endp 18)
        mockintosh = get_http_target('TARGET_MOCKINTOSH', authenticate)
        resp = mockintosh.get('/customers/undefined')
        resp.assert_jsonpath('$.lastName', expected_value='Name')
        id_ = jsonpath('$.id', resp)

        # GET http://mockintosh/customers/{id} (endp 17)
        resp = mockintosh.get(f'/customers/{id_}')
        resp.assert_jsonpath('$.lastName', expected_value='Name')

    # authentication-related test
    @clear_session({'spanId': 19})
    def test_19_get_login(self):
        # GET http://mockintosh/login (endp 19)
        mockintosh = get_http_target('TARGET_MOCKINTOSH', dummy_auth)
        resp = mockintosh.get('/login')
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')

    @clear_session({'spanId': 50})
    def test_50_get_tags(self):
        # GET http://mockintosh/tags (endp 50)
        mockintosh = get_http_target('TARGET_MOCKINTOSH', authenticate)
        resp = mockintosh.get('/tags')
        resp.assert_status_code(200)


@data_driven_tests
class Tests_orders(unittest.TestCase):

    @clear_session({'spanId': 14})
    def test_14_get_orders_search_customerId(self):
        # GET http://orders/orders/search/customerId (endp 14)
        orders = get_http_target('TARGET_ORDERS', authenticate)
        qstr = '?' + urlencode([('custId', 'undefined'), ('sort', 'date')])
        resp = orders.get('/orders/search/customerId' + qstr)
        resp.assert_status_code(200)


@data_driven_tests
class Tests_payment(unittest.TestCase):

    @clear_session({'spanId': 67})
    def test_67_post_paymentAuth(self):
        # POST http://payment/paymentAuth (endp 67)
        ccv = '958'
        country = 'United Kingdom'
        expires = '08/19'
        longNum = '5544154011345918'
        number = '246'
        postcode = 'G67 3DL'
        street = 'Whitelees Road'
        payment = get_http_target('TARGET_PAYMENT', authenticate)
        with open('data/payload_for_endp_67.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address.country', country)
        apply_into_json(json_payload, '$.address.number', number)
        apply_into_json(json_payload, '$.address.postcode', postcode)
        apply_into_json(json_payload, '$.address.street', street)
        apply_into_json(json_payload, '$.amount', float(random.uniform(4.99, 104.979996)))
        apply_into_json(json_payload, '$.card.ccv', ccv)
        apply_into_json(json_payload, '$.card.expires', expires)
        apply_into_json(json_payload, '$.card.longNum', longNum)
        resp = payment.post('/paymentAuth', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(200)


@data_driven_tests
class Tests_shipping(unittest.TestCase):

    @clear_session({'spanId': 68})
    def test_68_post_shipping(self):
        # POST http://shipping/shipping (endp 68)
        name = '57a98d98e4b00679b4a830b2'
        shipping = get_http_target('TARGET_SHIPPING', authenticate)
        with open('data/payload_for_endp_68.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.id', str(uuid.uuid4()))
        apply_into_json(json_payload, '$.name', name)
        resp = shipping.post('/shipping', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)


@data_driven_tests
class Tests_user(unittest.TestCase):

    @clear_session({'spanId': 15})
    def test_15_get_customers_customerId(self):
        # POST http://orders/orders (endp 57)
        address = 'http://user/addresses/57a98d98e4b00679b4a830b0'
        card = 'http://user/cards/57a98d98e4b00679b4a830b1'
        customer = 'http://user/customers/57a98d98e4b00679b4a830b2'
        items = 'http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items'
        orders = get_http_target('TARGET_ORDERS', authenticate)
        with open('data/payload_for_endp_57.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address', address)
        apply_into_json(json_payload, '$.card', card)
        apply_into_json(json_payload, '$.customer', customer)
        apply_into_json(json_payload, '$.items', items)
        resp = orders.post('/orders', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_status_code(201)
        resp.assert_jsonpath('$.address.city', expected_value='Glasgow')
        customerId = jsonpath('$.customerId', resp)

        # GET http://user/customers/{customerId} (endp 15)
        user = get_http_target('TARGET_USER', authenticate)
        resp = user.get(f'/customers/{customerId}')
        resp.assert_jsonpath('$.lastName', expected_value='Name')

    # authentication-related test
    @clear_session({'spanId': 16})
    def test_16_get_login(self):
        # GET http://user/login (endp 16)
        user = get_http_target('TARGET_USER', dummy_auth)
        resp = user.get('/login')
        resp.assert_jsonpath('$.user.lastName', expected_value='Name')
