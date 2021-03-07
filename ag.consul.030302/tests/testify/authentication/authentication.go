package authentication

type Authenticator interface {
    Authenticate(targetKey string)
}

type Authentication struct {

}

type DummyAuth struct {

}

func (a Authentication) Authenticate(targetKey string) {
    switch targetKey {
    case "TARGET_CARTS":
    case "TARGET_CATALOGUE":
    case "TARGET_FRONT_END":
    case "TARGET_MOCKINTOSH":
    case "TARGET_ORDERS":
    case "TARGET_USER":
    }
}

func (a DummyAuth) Authenticate(targetKey string) {
}
