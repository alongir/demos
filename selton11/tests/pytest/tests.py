from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_carts_sock_shop(unittest.TestCase):

    @json_dataset('data/dataset_32.json')
    @clear_session({'spanId': 32})
    def test_32_delete_carts_customerId(self, data_row):
        customerId, = data_row

        # DELETE http://carts.sock-shop/carts/{customerId} (endp 32)
        carts_sock_shop = get_http_client('http://carts.sock-shop', authenticate)
        resp = carts_sock_shop.delete(f'/carts/{customerId}')
        resp.assert_ok()
        # resp.assert_status_code(202)

    @json_dataset('data/dataset_18.json')
    @clear_session({'spanId': 18})
    def test_18_get_carts_customerId_items(self, data_row):
        customerId, = data_row

        # GET http://carts.sock-shop/carts/{customerId}/items (endp 18)
        carts_sock_shop = get_http_client('http://carts.sock-shop', authenticate)
        resp = carts_sock_shop.get(f'/carts/{customerId}/items', headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(200)

    @json_dataset('data/dataset_33.json')
    @clear_session({'spanId': 33})
    def test_33_post_carts_customerId_items(self, data_row):
        customerId, itemId, unitPrice = data_row

        # POST http://carts.sock-shop/carts/{customerId}/items (endp 33)
        carts_sock_shop = get_http_client('http://carts.sock-shop', authenticate)
        with open('data/payload_for_endp_33.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.itemId', itemId)
        apply_into_json(json_payload, '$.unitPrice', unitPrice)
        resp = carts_sock_shop.post(f'/carts/{customerId}/items', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(201)

    @json_dataset('data/dataset_34.json')
    @clear_session({'spanId': 34})
    def test_34_delete_carts_customerId_items_itemId(self, data_row):
        customerId, itemId = data_row

        # DELETE http://carts.sock-shop/carts/{customerId}/items/{itemId} (endp 34)
        carts_sock_shop = get_http_client('http://carts.sock-shop', authenticate)
        resp = carts_sock_shop.delete(f'/carts/{customerId}/items/{itemId}')
        resp.assert_ok()
        # resp.assert_status_code(202)

    @json_dataset('data/dataset_35.json')
    @clear_session({'spanId': 35})
    def test_35_get_carts_customerId_merge(self, data_row):
        customerId, sessionId = data_row

        # GET http://carts.sock-shop/carts/{customerId}/merge (endp 35)
        carts_sock_shop = get_http_client('http://carts.sock-shop', authenticate)
        qstr = '?' + urlencode([('sessionId', sessionId)])
        resp = carts_sock_shop.get(f'/carts/{customerId}/merge' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(202)


@data_driven_tests
class Tests_catalogue_sock_shop(unittest.TestCase):

    @json_dataset('data/dataset_24.json')
    @clear_session({'spanId': 24})
    def test_24_get_catalogue(self, data_row):
        size, = data_row

        # GET http://catalogue.sock-shop/catalogue (endp 24)
        catalogue_sock_shop = get_http_client('http://catalogue.sock-shop', authenticate)
        qstr = '?' + urlencode([('page', '1'), ('size', size), ('tags', '')])
        resp = catalogue_sock_shop.get('/catalogue' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(200)

    @json_dataset('data/dataset_25.json')
    @clear_session({'spanId': 25})
    def test_25_get_catalogue_id(self, data_row):
        id_, = data_row

        # GET http://catalogue.sock-shop/catalogue/{id} (endp 25)
        catalogue_sock_shop = get_http_client('http://catalogue.sock-shop', authenticate)
        resp = catalogue_sock_shop.get(f'/catalogue/{id_}')
        resp.assert_ok()
        # resp.assert_status_code(200)

    @clear_session({'spanId': 26})
    def test_26_get_catalogue_size(self):
        # GET http://catalogue.sock-shop/catalogue/size (endp 26)
        catalogue_sock_shop = get_http_client('http://catalogue.sock-shop', authenticate)
        qstr = '?' + urlencode([('tags', '')])
        resp = catalogue_sock_shop.get('/catalogue/size' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(200)

    @clear_session({'spanId': 27})
    def test_27_get_tags(self):
        # GET http://catalogue.sock-shop/tags (endp 27)
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

    @clear_session({'spanId': 2})
    def test_02_get_address(self):
        # GET http://front-end.sock-shop/address (endp 2)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        resp = front_end_sock_shop.get('/address', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$.city', expected_value='Glasgow')

    @clear_session({'spanId': 3})
    def test_03_get_basket_html(self):
        # GET http://front-end.sock-shop/basket.html (endp 3)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        resp = front_end_sock_shop.get('/basket.html')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#basket div.box form h1', expected_value='Shopping cart')

    @clear_session({'spanId': 4})
    def test_04_get_card(self):
        # GET http://front-end.sock-shop/card (endp 4)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        resp = front_end_sock_shop.get('/card', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(200)

    @clear_session({'spanId': 5})
    def test_05_get_cart(self):
        # GET http://front-end.sock-shop/cart (endp 5)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        resp = front_end_sock_shop.get('/cart', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(200)

    @clear_session({'spanId': 6})
    def test_06_delete_cart(self):
        # DELETE http://front-end.sock-shop/cart (endp 6)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        resp = front_end_sock_shop.delete('/cart', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(202)

    @json_dataset('data/dataset_7.json')
    @clear_session({'spanId': 7})
    def test_07_post_cart(self, data_row):
        id_, = data_row

        # POST http://front-end.sock-shop/cart (endp 7)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        with open('data/payload_for_endp_7.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.id', id_)
        resp = front_end_sock_shop.post('/cart', json=json_payload, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(201)

    @json_dataset('data/dataset_8.json')
    @clear_session({'spanId': 8})
    def test_08_delete_cart_itemId(self, data_row):
        itemId, = data_row

        # DELETE http://front-end.sock-shop/cart/{itemId} (endp 8)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        resp = front_end_sock_shop.delete(f'/cart/{itemId}', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(202)

    @json_dataset('data/dataset_11.json')
    @clear_session({'spanId': 11})
    def test_11_get_catalogue(self, data_row):
        size, = data_row

        # GET http://front-end.sock-shop/catalogue (endp 11)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        qstr = '?' + urlencode([('page', '1'), ('size', size), ('tags', '')])
        resp = front_end_sock_shop.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(200)

    @json_dataset('data/dataset_9.json')
    @clear_session({'spanId': 9})
    def test_09_get_catalogue_id(self, data_row):
        id_, = data_row

        # GET http://front-end.sock-shop/catalogue/{id} (endp 9)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        resp = front_end_sock_shop.get(f'/catalogue/{id_}', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(200)

    @clear_session({'spanId': 10})
    def test_10_get_catalogue_size(self):
        # GET http://front-end.sock-shop/catalogue/size (endp 10)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        qstr = '?' + urlencode([('tags', '')])
        resp = front_end_sock_shop.get('/catalogue/size' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(200)

    @clear_session({'spanId': 12})
    def test_12_get_category_html(self):
        # GET http://front-end.sock-shop/category.html (endp 12)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        resp = front_end_sock_shop.get('/category.html')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title', expected_value='Filters ')

    @json_dataset('data/dataset_13.json')
    @clear_session({'spanId': 13})
    def test_13_get_customers_customerId(self, data_row):
        customerId, = data_row

        # GET http://front-end.sock-shop/customers/{customerId} (endp 13)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        resp = front_end_sock_shop.get(f'/customers/{customerId}', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$.lastName', expected_value='Name')

    @json_dataset('data/dataset_14.json')
    @clear_session({'spanId': 14})
    def test_14_get_detail_html(self, data_row):
        id_, = data_row

        # GET http://front-end.sock-shop/detail.html (endp 14)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        qstr = '?' + urlencode([('id', id_)])
        resp = front_end_sock_shop.get('/detail.html' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#content div.container div div.row.same-height-row div div.box.same-height h3', expected_value='You may also like these products')

    @clear_session({'spanId': 15})
    def test_15_get_login(self):
        # GET http://front-end.sock-shop/login (endp 15)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        resp = front_end_sock_shop.get('/login', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('p', expected_value='Cookie is set')

    @clear_session({'spanId': 16})
    def test_16_post_orders(self):
        # POST http://front-end.sock-shop/orders (endp 16)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        resp = front_end_sock_shop.post('/orders', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(201)
        # resp.assert_jsonpath('$.address.city', expected_value='Glasgow')

    @clear_session({'spanId': 17})
    def test_17_get_tags(self):
        # GET http://front-end.sock-shop/tags (endp 17)
        front_end_sock_shop = get_http_client('http://front-end.sock-shop', authenticate)
        resp = front_end_sock_shop.get('/tags', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(200)


@data_driven_tests
class Tests_kafka(unittest.TestCase):

    @json_dataset('data/dataset_39.json')
    @clear_session({'spanId': 39})
    def test_39_put_shipping_task(self, data_row):
        name, = data_row

        # PUT kafka://kafka/shipping-task (endp 39)
        kafka = Kafka('TARGET_KAFKA')
        with open('data/payload_for_endp_39.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.id', str(uuid.uuid4()))
        apply_into_json(json_payload, '$.name', name)
        resp = kafka.put('/shipping-task', json=json_payload)


@data_driven_tests
class Tests_orders_sock_shop(unittest.TestCase):

    @json_dataset('data/dataset_37.json')
    @clear_session({'spanId': 37})
    def test_37_post_orders(self, data_row):
        address, card, customer, items = data_row

        # POST http://orders.sock-shop/orders (endp 37)
        orders_sock_shop = get_http_client('http://orders.sock-shop', authenticate)
        with open('data/payload_for_endp_37.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address', address)
        apply_into_json(json_payload, '$.card', card)
        apply_into_json(json_payload, '$.customer', customer)
        apply_into_json(json_payload, '$.items', items)
        resp = orders_sock_shop.post('/orders', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(201)
        # resp.assert_jsonpath('$.address.city', expected_value='Glasgow')

    @json_dataset('data/dataset_38.json')
    @clear_session({'spanId': 38})
    def test_38_get_orders_search_customerId(self, data_row):
        custId, = data_row

        # GET http://orders.sock-shop/orders/search/customerId (endp 38)
        orders_sock_shop = get_http_client('http://orders.sock-shop', authenticate)
        qstr = '?' + urlencode([('custId', custId), ('sort', 'date')])
        resp = orders_sock_shop.get('/orders/search/customerId' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(200)


@data_driven_tests
class Tests_payment_sock_shop(unittest.TestCase):

    @json_dataset('data/dataset_22.json')
    @clear_session({'spanId': 22})
    def test_22_post_paymentAuth(self, data_row):
        ccv, country, expires, longNum, number, postcode, street = data_row

        # POST http://payment.sock-shop/paymentAuth (endp 22)
        payment_sock_shop = get_http_client('http://payment.sock-shop', authenticate)
        with open('data/payload_for_endp_22.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.address.country', country)
        apply_into_json(json_payload, '$.address.number', number)
        apply_into_json(json_payload, '$.address.postcode', postcode)
        apply_into_json(json_payload, '$.address.street', street)
        apply_into_json(json_payload, '$.amount', float(random.uniform(4.99, 270.96997)))
        apply_into_json(json_payload, '$.card.ccv', ccv)
        apply_into_json(json_payload, '$.card.expires', expires)
        apply_into_json(json_payload, '$.card.longNum', longNum)
        resp = payment_sock_shop.post('/paymentAuth', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(200)


@data_driven_tests
class Tests_shipping_sock_shop(unittest.TestCase):

    @json_dataset('data/dataset_23.json')
    @clear_session({'spanId': 23})
    def test_23_post_shipping(self, data_row):
        name, = data_row

        # POST http://shipping.sock-shop/shipping (endp 23)
        shipping_sock_shop = get_http_client('http://shipping.sock-shop', authenticate)
        with open('data/payload_for_endp_23.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.id', str(uuid.uuid4()))
        apply_into_json(json_payload, '$.name', name)
        resp = shipping_sock_shop.post('/shipping', json=json_payload, headers=dict([('accept', 'application/json')]))
        resp.assert_ok()
        # resp.assert_status_code(201)


@data_driven_tests
class Tests_user_sock_shop(unittest.TestCase):

    @json_dataset('data/dataset_19.json')
    @clear_session({'spanId': 19})
    def test_19_get_addresses_addresseId(self, data_row):
        addresseId, = data_row

        # GET http://user.sock-shop/addresses/{addresseId} (endp 19)
        user_sock_shop = get_http_client('http://user.sock-shop', authenticate)
        resp = user_sock_shop.get(f'/addresses/{addresseId}', headers=dict([('accept', 'application/hal+json')]))
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$.city', expected_value='Glasgow')

    @json_dataset('data/dataset_20.json')
    @clear_session({'spanId': 20})
    def test_20_get_cards_cardId(self, data_row):
        cardId, = data_row

        # GET http://user.sock-shop/cards/{cardId} (endp 20)
        user_sock_shop = get_http_client('http://user.sock-shop', authenticate)
        resp = user_sock_shop.get(f'/cards/{cardId}', headers=dict([('accept', 'application/hal+json')]))
        resp.assert_ok()
        # resp.assert_status_code(200)

    @json_dataset('data/dataset_21.json')
    @clear_session({'spanId': 21})
    def test_21_get_customers_customerId(self, data_row):
        customerId, = data_row

        # GET http://user.sock-shop/customers/{customerId} (endp 21)
        user_sock_shop = get_http_client('http://user.sock-shop', authenticate)
        resp = user_sock_shop.get(f'/customers/{customerId}', headers=dict([('accept', 'application/hal+json')]))
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$.lastName', expected_value='Name')

    @json_dataset('data/dataset_29.json')
    @clear_session({'spanId': 29})
    def test_29_get_customers_customerId_addresses(self, data_row):
        customerId, = data_row

        # GET http://user.sock-shop/customers/{customerId}/addresses (endp 29)
        user_sock_shop = get_http_client('http://user.sock-shop', authenticate)
        resp = user_sock_shop.get(f'/customers/{customerId}/addresses')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$._embedded.address[*].city', expected_value='Glasgow')

    @json_dataset('data/dataset_30.json')
    @clear_session({'spanId': 30})
    def test_30_get_customers_customerId_cards(self, data_row):
        customerId, = data_row

        # GET http://user.sock-shop/customers/{customerId}/cards (endp 30)
        user_sock_shop = get_http_client('http://user.sock-shop', authenticate)
        resp = user_sock_shop.get(f'/customers/{customerId}/cards')
        resp.assert_ok()
        # resp.assert_status_code(200)

    @clear_session({'spanId': 31})
    def test_31_get_login(self):
        # GET http://user.sock-shop/login (endp 31)
        user_sock_shop = get_http_client('http://user.sock-shop', authenticate)
        resp = user_sock_shop.get('/login')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_jsonpath('$.user.lastName', expected_value='Name')
