from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_edge_router(unittest.TestCase):

    @clear_session({'spanId': 1})
    def test_1_get_(self):
        # GET http://edge-router/ (endp 1)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    @clear_session({'spanId': 2})
    def test_2_get_catalogue(self):
        # GET http://edge-router/catalogue (endp 2)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/catalogue')
        resp.assert_ok()
        # resp.assert_status_code(200)

    @clear_session({'spanId': 3})
    def test_3_get_category_html(self):
        # GET http://edge-router/category.html (endp 3)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        resp = edge_router.get('/category.html')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title', expected_value='Filters ')

    @json_dataset('data/dataset_4.json')
    @clear_session({'spanId': 4})
    def test_4_get_detail_html(self, data_row):
        id_, = data_row

        # GET http://edge-router/detail.html (endp 4)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', authenticate)
        qstr = '?' + urlencode([('id', id_)])
        resp = edge_router.get('/detail.html' + qstr)
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#content div.container div div.row.same-height-row div div.box.same-height h3', expected_value='You may also like these products')

    # authentication-related test
    @clear_session({'spanId': 5})
    def test_5_get_login(self):
        # GET http://edge-router/login (endp 5)
        edge_router = get_http_target('TARGET_EDGE_ROUTER', dummy_auth)
        resp = edge_router.get('/login')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('p', expected_value='Cookie is set')


@data_driven_tests
class Tests_front_end(unittest.TestCase):

    @clear_session({'spanId': 6})
    def test_6_get_(self):
        # GET http://front-end/ (endp 6)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/')
        resp.assert_ok()
        # resp.assert_status_code(200)
        # resp.assert_cssselect('div#hot div.box div.container div h2', expected_value='Hot this week')

    @clear_session({'spanId': 7})
    def test_7_get_catalogue(self):
        # GET http://front-end/catalogue (endp 7)
        front_end = get_http_target('TARGET_FRONT_END', authenticate)
        resp = front_end.get('/catalogue')
        resp.assert_ok()
        # resp.assert_status_code(200)
