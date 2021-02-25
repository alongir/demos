from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_carts(unittest.TestCase):

    @json_dataset('data/dataset_12.json')
    @clear_session({'spanId': 12})
    def test_12_get_carts_cartId_items(self, data_row):
        cartId, = data_row

        # GET http://carts/carts/{cartId}/items (endp 12)
        carts = get_http_target('TARGET_CARTS', authenticate)
        resp = carts.get(f'/carts/{cartId}/items')
        resp.assert_ok()
        # resp.assert_status_code(200)


@data_driven_tests
class Tests_catalogue(unittest.TestCase):

    @json_dataset('data/dataset_11.json')
    @clear_session({'spanId': 11})
    def test_11_get_catalogue(self, data_row):
        size, = data_row

        # GET http://catalogue/catalogue (endp 11)
        catalogue = get_http_target('TARGET_CATALOGUE', authenticate)
        qstr = '?' + urlencode([('size', size)])
        resp = catalogue.get('/catalogue' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(200)


@data_driven_tests
class Tests_front_end(unittest.TestCase):

    @clear_session({'spanId': 1})
    def test_01_get_cart(self):
        # GET http://front-end/cart (endp 1)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/cart', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(200)

    @json_dataset('data/dataset_2.json')
    @clear_session({'spanId': 2})
    def test_02_get_catalogue(self, data_row):
        size, = data_row

        # GET http://front-end/catalogue (endp 2)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        qstr = '?' + urlencode([('size', size)])
        resp = front_end.get('/catalogue' + qstr, headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_ok()
        # resp.assert_status_code(200)

    @clear_session({'spanId': 3})
    def test_03_get_(self):
        # GET http://front-end/ (endp 3)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    @clear_session({'spanId': 4})
    def test_04_get_basket_html(self):
        # GET http://front-end/basket.html (endp 4)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/basket.html')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#basket div.box form h1', expected_value='Shopping cart')

    @clear_session({'spanId': 5})
    def test_05_delete_cart(self):
        # DELETE http://front-end/cart (endp 5)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.delete('/cart')
        resp.assert_ok()
        # resp.assert_status_code(202)

    @json_dataset('data/dataset_6.json')
    @clear_session({'spanId': 6})
    def test_06_post_cart(self, data_row):
        id_, = data_row

        # POST http://front-end/cart (endp 6)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        with open('data/payload_for_endp_6.json', 'r') as json_payload_file:
            json_payload = json.load(json_payload_file)
        apply_into_json(json_payload, '$.id', id_)
        resp = front_end.post('/cart', json=json_payload)
        resp.assert_ok()
        # resp.assert_status_code(201)

    @clear_session({'spanId': 7})
    def test_07_get_category_html(self):
        # GET http://front-end/category.html (endp 7)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/category.html')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title', expected_value='Filters ')

    @json_dataset('data/dataset_8.json')
    @clear_session({'spanId': 8})
    def test_08_get_detail_html(self, data_row):
        id_, = data_row

        # GET http://front-end/detail.html (endp 8)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        qstr = '?' + urlencode([('id', id_)])
        resp = front_end.get('/detail.html' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#content div.container div div.row.same-height-row div div.box.same-height h3', expected_value='You may also like these products')

    # authentication-related test
    @clear_session({'spanId': 9})
    def test_09_get_login(self):
        # GET http://front-end/login (endp 9)
        front_end = get_http_target('TARGET_FRONT_END', dummy_auth)
        resp = front_end.get('/login')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('p', expected_value='Cookie is set')

    @clear_session({'spanId': 10})
    def test_10_post_orders(self):
        # POST http://front-end/orders (endp 10)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.post('/orders')
        resp.assert_ok()
        # resp.assert_status_code(201)
        # resp.assert_jsonpath('$.address.city', expected_value='Glasgow')
