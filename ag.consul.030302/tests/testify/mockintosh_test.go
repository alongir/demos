package up9_autogenerated_tests

import (
    . "authentication"
    . "up9lib"
    assert "github.com/stretchr/testify/assert"
    testing "testing"
)

func TestGet39(t *testing.T) {
    // GET http://mockintosh/ (endp 39)
    mockintosh := GetHttpTarget(t, "TARGET_MOCKINTOSH", new(Authentication))
    req := new(HttpRequest)
    resp := mockintosh.Get(req, "/")
    assert.Equal(t, 200, resp.StatusCode())
    assert.Contains(t, CssSelect(t, "div#hot div.box div.container div h2", resp), "Hot this week")
}

func TestGetCatalogue47(t *testing.T) {
    // GET http://mockintosh/catalogue (endp 47)
    mockintosh := GetHttpTarget(t, "TARGET_MOCKINTOSH", new(Authentication))
    req := new(HttpRequest)
    resp := mockintosh.Get(req, "/catalogue")
    assert.Equal(t, 200, resp.StatusCode())
}

func TestGetCustomersId17(t *testing.T) {
    // GET http://mockintosh/customers/undefined (endp 18)
    mockintosh := GetHttpTarget(t, "TARGET_MOCKINTOSH", new(Authentication))
    req := new(HttpRequest)
    resp := mockintosh.Get(req, "/customers/undefined")
    assert.Equal(t, 200, resp.StatusCode())
    assert.Equal(t, "Name", JsonPath(t, "$.lastName", resp.String()))
    id := JsonPath(t, "$.id", resp.String())

    // GET http://mockintosh/customers/{id} (endp 17)
    req2 := new(HttpRequest)
    resp2 := mockintosh.Get(req2, "/customers/" + id)
    assert.Equal(t, 200, resp2.StatusCode())
    assert.Equal(t, "Name", JsonPath(t, "$.lastName", resp2.String()))
}

// authentication-related test
func TestGetLogin19(t *testing.T) {
    // GET http://mockintosh/login (endp 19)
    mockintosh := GetHttpTarget(t, "TARGET_MOCKINTOSH", new(DummyAuth))
    req := new(HttpRequest)
    resp := mockintosh.Get(req, "/login")
    assert.Equal(t, 200, resp.StatusCode())
    assert.Equal(t, "Name", JsonPath(t, "$.user.lastName", resp.String()))
}
