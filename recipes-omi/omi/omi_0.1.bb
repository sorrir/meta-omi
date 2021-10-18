SUMMARY = "Recipe for using inherit useradd"
DESCRIPTION = "Adds a specific user with supplied keys"
LICENSE = "CLOSED"

SRC_URI = "file://authorized_keys"


inherit useradd

USERADD_PACKAGES = "${PN}"

USERADD_PARAM_${PN} = "-u 1000 -N -d /home/omi -m -s /bin/sh omi"

GROUPADD_PARAM_${PN} = "-g 1000 omi"

do_install () {
	install -d -m 755 ${D}/home/omi/.ssh
	install -p -m 600 ${WORKDIR}/authorized_keys ${D}/home/omi/.ssh
	chown -R omi ${D}/home/omi/.ssh
	chgrp -R omi ${D}/home/omi/.ssh
}

FILES_${PN} = "/home"

# Prevents do_package failures with:
# debugsources.list: No such file or directory:
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
