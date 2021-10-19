# meta-omi

An OpenEmbedded Layer that provides recipes to configure network, users and mender access. This is heavily opinionated and use-case specific. There are no configuration interfaces intended to be be used in a `local.conf`. This rather acts as a base that should be edited directly.

## Layer dependencies

This layer depends on:

```bb
  URI: git://git@github.com:mendersoftware/meta-mender.git
  branch: warrior
```

## Installation

Add the omi layer in your `local.conf`:

```sh
IMAGE_INSTALL_append = " omi"
```

All the other recipes use `.bbappend`s and are therefore applied automatically.

## Configuration

Change the files in the recipes files folder

### recipes-omi

#### omi bbclass

Creates a user called "omi" with the public keys listed in [authorized_keys](recipes-omi/omi/files/authorized_keys). Change accordingly.

### recipes-mender

#### mender bbappend

* requires mender
* sets the central mender server endpoint and certificate
* Edit [server.crt](recipes-mender/mender/files/server.crt) with your mender certificate
* Edit [mender_%.bbappend](recipes-mender/mender/mender_%25.bbappend) to set your mender endpoint and configuration

### recipes-core

#### systemd-conf bbappend

This generic bbappend enables dhcp on common interface names.

#### systemd bbappend

This makes sure `networkd resolved polkit` are installend

#### dropbear bbappend

This configures the ssh server. Specifically, it disables password logins.
