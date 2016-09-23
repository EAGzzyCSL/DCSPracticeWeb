$(document).ready(function () {
    var domain = $_GET["domain"];
    $("#title").text(domain);

    $.post(BASE_URL + "get_searchID", {domain: domain}, function (data) {
        if (data == "") {
            alert("暂无记录");
            return;
        }

        var id = data[0].id;
        getDomainSample(id);
        getDomainIP(id);
        getDomainRecord(id);
        getDomainList(id);
        getChildrenDomain(id);
        getDomainWhois(id);
        gTree("city_tree.json");
    });
});

// 通讯样本
function getDomainSample(id) {
    $.post(BASE_URL + "get_domainSample", {ID: id}, function (data) {
        for (var i = 0; i < data.length; i++) {
            var tr = $("<tr></tr>");
            var td, a;

            td = $("<td></td>");
            a = $("<a target='_blank'></a>");
            a.text(data[i].s_sha256);
            a.attr("href", "resultFile.html?sha256=" + data[i].s_sha256);
            td.append(a);
            tr.append(td);

            $("#threat_sample").append(tr);
        }
    });
}

// IP地址
function getDomainIP(id) {
    $.post(BASE_URL + "get_domainIP", {domain: id}, function (data) {
        var a = $("#ip_address");
        a.text(data[0].IP_Info);
        a.attr("href", "resultIP.html?ip=" + data[0].IP_Info);
    });
}

// 历史解析记录
function getDomainRecord(id) {
    $.post(BASE_URL + "get_domainRecord", {ID: id}, function (data) {
        for (var i = 0; i < data.length; i++) {
            var tr = $("<tr></tr>");
            var td, a;

            td = $("<td></td>");
            td.text(data[i].di_time);
            tr.append(td);

            td = $("<td></td>");
            a = $("<a target='_blank'></a>");
            a.text(data[i].ip_info);
            a.attr("href", "resultIP.html?ip=" + data[i].ip_info);
            td.append(a);
            tr.append(td);

            $("#history_ip").append(tr);
        }
    });
}

// 指向同一IP的域名列表
function getDomainList(id) {
    $.post(BASE_URL + "get_domainList", {ID: id}, function (data) {
        for (var i = 0; i < data.length; i++) {
            var tr = $("<tr></tr>");
            var td, a;

            td = $("<td></td>");
            a = $("<a target='_blank'></a>");
            a.text(data[i].D_name);
            a.attr("href", "resultDomain.html?domain=" + data[i].D_name);
            td.append(a);
            tr.append(td);

            $("#same_ip_domain").append(tr);
        }
    });
}

// 子域名
function getChildrenDomain(id) {
    $.post(BASE_URL + "get_childrenDomain", {ID: id}, function (data) {
        for (var i = 0; i < data.length; i++) {
            var tr = $("<tr></tr>");
            var td, a;

            td = $("<td></td>");
            a = $("<a target='_blank'></a>");
            a.text(data[i].d_name);
            a.attr("href", "resultDomain.html?domain=" + data[i].d_name);
            td.append(a);
            tr.append(td);

            $("#sub_domain").append(tr);
        }
    });
}

// 当前注册信息
function getDomainWhois(id) {
    $.post(BASE_URL + "get_domainWhois", {ID: id}, function (data) {
        $("#d_registrar").text(data[0].d_registar);
        $("#d_ra").text(data[0].d_ra);
        $("#d_mailbox").text(data[0].d_mailbox);
        $("#d_address").text(data[0].d_address);
        $("#d_phone").text(data[0].d_phone);
        $("#d_ot").text(data[0].d_ot);
        $("#d_ut").text(data[0].d_ut);
        $("#d_sp").text(data[0].d_sp);
        $("#d_s").text(data[0].d_s);
    });
}