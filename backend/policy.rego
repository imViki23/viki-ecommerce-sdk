package authz

import rego.v1

default allow := false

allow if {
    "ADMIN" in input.roles
}