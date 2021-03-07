UP9 - Automatically Generated Go (Testify) Tests


Requirements:

Install dependencies:

There is a `go.mod` file in the root directory. So it will automatically install the
dependencies when you run the tests. To update `go.mod`:

```
go get -v -t ./...
```

Running the tests:

`go test -v`

Running a single test:

`go test module_name_test.go -v -run TestFunctionName`
