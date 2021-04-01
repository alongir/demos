from up9lib import *
from authentication import authenticate

# logging.basicConfig(level=logging.DEBUG)


@data_driven_tests
class Tests_front_end(unittest.TestCase):

    @json_dataset('data/dataset_13.json')
    @clear_session({'spanId': 13})
    def test_13_get_catalogue_id(self, data_row):
        id_, = data_row

        # GET http://front-end/catalogue/{id} (endp 13)
        front_end = get_http_client('http://front-end', authenticate)
        resp = front_end.get(f'/catalogue/{id_}', headers=dict([('x-requested-with', 'XMLHttpRequest')]))
        resp.assert_status_code(200)
