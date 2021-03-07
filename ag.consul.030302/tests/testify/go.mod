module up9.com/tests

go 1.15

require (
    github.com/go-resty/resty/v2 v2.4.0
    github.com/google/uuid v1.2.0
    github.com/stretchr/testify v1.7.0
    github.com/ohler55/ojg v1.7.0
    github.com/PuerkitoBio/goquery v1.6.1
    up9lib v0.0.0-00010101000000-000000000000
    authentication v0.0.0-00010101000000-000000000000
)

replace up9lib => ./up9lib
replace authentication => ./authentication
