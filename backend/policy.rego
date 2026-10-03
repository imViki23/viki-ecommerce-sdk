package authz

import rego.v1

default allow := false

allow if {
    "9d3349bc-7c58-41aa-88fa-88cac3fe5f59" in input.roles
}