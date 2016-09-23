$(document).ready(function () {
    var ip = $_GET["ip"];
    $("#title").text(ip);

    $.post(BASE_URL + "get_searchID", {ip: ip}, function (data) {
        if (data == "") {
            alert("暂无记录");
            return;
        }

        var id = data[0].id;
        getIPSample(id);
        getDomainList(id);
        getIPDomainRecord(id);
        gTree("city_tree.json");
    });
});

// 通讯样本
function getIPSample(id) {
    $.post(BASE_URL + "get_ipSample", {ID: id}, function (data) {
        for (var i = 0; i < data.length; i++) {
            var tr = $("<tr></tr>");
            var td, a;

            td = $("<td></td>");
            a = $("<a target='_blank'></a>");
            a.text(data[i].S_Sha256);
            a.attr("href", "resultFile.html?sha256=" + data[i].S_Sha256);
            td.append(a);
            tr.append(td);

            $("#threat_sample").append(tr);
        }
    });
}

// 指向该IP的域名
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

// 历史域名记录
function getIPDomainRecord(id) {
    $.post(BASE_URL + "get_ipDomainRecord", {ID: id}, function (data) {
        for (var i = 0; i < data.length; i++) {
            var tr = $("<tr></tr>");
            var td, a;

            td = $("<td></td>");
            td.text(data[i].di_time);
            tr.append(td);

            td = $("<td></td>");
            a = $("<a target='_blank'></a>");
            a.text(data[i].d_name);
            a.attr("href", "resultDomain.html?domain=" + data[i].d_name);
            td.append(a);
            tr.append(td);

            $("#history_domain").append(tr);
        }
    });
}