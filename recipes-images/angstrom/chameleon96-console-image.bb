# this is not available in kraj/meta-altera so point to console-image.bb instead
# need to add additional packages below (dn 1/19/17)
#require recipes-images/angstrom/extended-console-image.bb
require recipes-images/angstrom/console-image.bb

# add next line back in (below chameleon96-lighttpd-conf \) when chameleon96 webcontent is available
#	chameleon96-webcontent (backslash)
	
IMAGE_INSTALL += " \
	kernel-modules \
	kernel-dev \
	linux-firmware \
	usbutils \
	libusbg \
	iw \
        systemd-analyze \
        vim vim-vimrc \
        procps \
        screen minicom \
        cronie-systemd ntpdate \
        tar \
        packagegroup-sdk-target \
	gcc \
        gdb gdbserver \
        iproute2 \
	lighttpd \
        lighttpd-module-cgi \
	chameleon96-lighttpd-conf \
	chameleon96-usb-gadget \
	gnuplot \
	cmake \
"
export IMAGE_BASENAME = "chameleon96-console-image"

#overload timestamp function in image.bbclass

rootfs_update_timestamp () {
        date -u +%4Y%2m%2d%2H%2M -d "+1 day">${IMAGE_ROOTFS}/etc/timestamp
}

EXPORT_FUNCTIONS rootfs_update_timestamp
