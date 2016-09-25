$(document).ready(function () {
    var sha1 = $_GET["sha1"];
    var sha256 = $_GET["sha256"];
    if (typeof (sha1) != "undefined") $("#title").text(sha1);
    else if (typeof (sha256) != "undefined") $("#title").text(sha256);

    $.post(BASE_URL + "get_searchID", {sha1: sha1, sha256: sha256}, function (data) {
        if (data == "") {
            alert("暂无记录");
            return;
        }

        var id = data[0].id;
        getResult(id);
        getSample(id);
        getSection(id);
        getImport(id);
        getFile(id);
        getRegistry(id);
        getMutex(id);
        getSampleDomain(id);
        gTree(BASE_URL + "plot_sample?ID=" + id);
    });
});

// 检测结果
function getResult(id) {
    $.post(BASE_URL + "get_result", {ID: id}, function (data) {
        for (var i = 0; i < data.length; i++) {
            var tr = $("<tr></tr>");
            var td;

            td = $("<td></td>");
            td.text(data[i].r_software);
            tr.append(td);

            td = $("<td></td>");
            td.text(data[i].r_result);
            tr.append(td);

            $("#scan_result").append(tr);
        }
    });
}

// 文件识别
function getSample(id) {
    $.post(BASE_URL + "get_sample", {ID: id}, function (data) {
        $("#s_ft").text(data[0].S_FT);
        $("#s_size").text(data[0].S_Size);
        $("#s_md5").text(data[0].S_MD5);
        $("#s_sha1").text(data[0].S_Sha1);
        $("#s_sha256").text(data[0].S_Sha256);
        $("#s_sha512").text(data[0].S_Sha512);
        $("#s_ssdeep").text(data[0].S_Ssdeep);
    });
}

// PE节信息
function getSection(id) {
    $.post(BASE_URL + "get_section", {ID: id}, function (data) {
        for (var i = 0; i < data.length; i++) {
            var tr = $("<tr></tr>");
            var td;

            td = $("<td></td>");
            td.text(data[i].pes_name);
            tr.append(td);

            td = $("<td></td>");
            td.text(data[i].pes_address);
            tr.append(td);

            td = $("<td></td>");
            td.text(data[i].pes_size);
            tr.append(td);

            td = $("<td></td>");
            td.text(data[i].pes_srd);
            tr.append(td);

            td = $("<td></td>");
            td.text(data[i].pes_entropy);
            tr.append(td);

            $("#pe_section").append(tr);
        }
    });
}

// 导入表
function getImport(id) {
    $.post(BASE_URL + "get_import", {ID: id}, function (data) {
        for (var i = 0; i < data.length; i++) {
            var tr = $("<tr></tr>");
            var td;

            td = $("<td></td>");
            td.text(data[i].PEI_DllName);
            tr.append(td);

            $("#import_dll").append(tr);
        }
    });
}

// 文件操作
function getFile(id) {
    $.post(BASE_URL + "get_file", {ID: id}, function (data) {
        for (var i = 0; i < data.length; i++) {
            var tr = $("<tr></tr>");
            var td;

            td = $("<td></td>");
            td.text(data[i].f_catalog);
            tr.append(td);

            $("#op_file").append(tr);
        }
    });
}

// 注册表操作
function getRegistry(id) {
    $.post(BASE_URL + "get_registry", {ID: id}, function (data) {
        for (var i = 0; i < data.length; i++) {
            var tr = $("<tr></tr>");
            var td;

            td = $("<td></td>");
            td.text(data[i].r_catalog);
            tr.append(td);

            $("#op_registry").append(tr);
        }
    });
}

// 互斥体操作
function getMutex(id) {
    $.post(BASE_URL + "get_mutex", {ID: id}, function (data) {
        for (var i = 0; i < data.length; i++) {
            var tr = $("<tr></tr>");
            var td;

            td = $("<td></td>");
            td.text(data[i].M_Object);
            tr.append(td);

            $("#op_mutex").append(tr);
        }
    });
}

// 网络活动
function getSampleDomain(id) {
    $.post(BASE_URL + "get_sampleDomain", {ID: id}, function (data) {
        for (var i = 0; i < data.length; i++) {
            var tr = $("<tr></tr>");
            var td, a;

            td = $("<td></td>");
            a = $("<a target='_blank'></a>");
            a.text(data[i].d_name);
            a.attr("href", "resultDomain.html?domain=" + data[i].d_name);
            td.append(a);
            tr.append(td);

            td = $("<td></td>");
            a = $("<a target='_blank'></a>");
            a.text(data[i].ip_info);
            a.attr("href", "resultIP.html?ip=" + data[i].ip_info);
            td.append(a);
            tr.append(td);

            $("#internet_activity").append(tr);
        }
    });
}