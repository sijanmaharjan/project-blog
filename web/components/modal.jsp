<div id="general-modal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
        </div>
    </div>
</div>

<script>
    function showGeneralModal(content) {
        $('#general-modal').children('div.modal-dialog').children('div.modal-content').html($(content).html());
        $('#general-modal').modal('show');
    }
    function hideGeneralModal() {
        $('#general-modal').modal('hide');
    }
</script>